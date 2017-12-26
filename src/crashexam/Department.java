package crashexam;
import crashexam.Job.Job;
import crashexam.Job.JobState;
import crashexam.Printers.*;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class implements the behaviour expected from the CRASH
 * system specified in interface Management required for 
 * 6COM1037 Object Oriented Design Exam - Jan 2018
 *
 * @author A.A.Marczyk
 * @version 15/10/17
 */
public class Department implements Management
{
    private String location;
    
    //Removed, can use just Printer now
    //private HashMap<String,Plain> allPlainPrinters = new HashMap<String,Plain>();
    //private HashMap<String,Laser> allLaserPrinters = new HashMap<String,Laser>();
    //private HashMap<String,BubbleJet> allBubblePrinters = new HashMap<String,BubbleJet>();
    
    
    private HashMap<String, Printer> allPrinters;
    
    private ArrayList<Job> allJobs;
    
    //Job ID counter
    private int jobCounter;

//**************** Department ************************** 
    /**
     * Constructor for a department loads all the printers from myprinters.txt text file
     * @site the location of the department
     */
    public Department(String site)
    {
        this(site, "myprinters.txt");   //Constructor overloading
    }
    
    /**
     * Constructor for a department loads all the printers from specified text file
     * @site the location of the department
     * @filename the name of the file which holds information about printers
     */
    public Department(String site, String filename)
    {
        allPrinters = new HashMap<String, Printer>();
        allJobs = new ArrayList<Job>();
        jobCounter = 100;
        location = site;
        readPrinters(filename);
    }
    
    /**Returns a String representation of the department,including
     * its location and all of its printers
     * @return a String representation of the department,including
     * its location and all its printers
     **/
    public String toString()
    {
        String s = "Department: " + location ;
        s = s + "\nAll Printers:\n" + getAllPrinters() ;
        return s;
    }
    
    // ***************** Printers ************************
    /** Returns a String representation of all the printers  
     * @return returns a String representation of all printers 
     **/
    public String getAllPrinters()
    {
        StringBuilder sb = new StringBuilder();
        
        for (Map.Entry<String, Printer> entry: allPrinters.entrySet()) {
            sb.append("ID=" + entry.getKey() + " " + entry.getValue().toString() + System.getProperty("line.separator"));
        }
        
        return sb.toString();
    }
    
    /** Returns true if the printer with the departmental identifier 
     * can be found in the system, false otherwise.
     * @param depId represents the departmental identifier of the printer
     * @return returns true if the printer with the departmental identifier 
     * can be found, false otherwise.
     **/
    public boolean isPrinter(String depId)
    {
        return allPrinters.containsKey(depId);
    }
    
    /** Removes a printer from the department.
     * pre-condition: isPrinter(depId)
     * @param depId represents the departmental identifier of the printer
     **/
    public void removePrinter(String depId)
    {
        allPrinters.remove(depId);
    }


//************************************************************** 
    /** Adds a job to the job list if acceptable.Jobs requiring high quality or 
     * colour printing of more than 200 copies are not acceptable, and 0 is returned.
     * Jobs which are acceptable are numbered sequentially from 100 and added to the 
     * list.If a suitable printer is available,the state of the job is set to 
     * "printing",the selected printer is added to the job, the printer's state is 
     * set to "in use" and 1 is returned. If a printer is not available, job's state
     * is set to "waiting"and 1 is returned. <-- This should say 2 not 1
     * @param cust is the name of the customer
     * @param staff indicates whether the customer is a member of staff
     * @param custNo is either staff number if staff, or student number,if student
     * @param sides is the number of sides in the document
     * @param copies is the number of copies required
     * @param col indicates whether colour is required
     * @param hQuality indicates whether high quality is required
     * @param dsided indicates whether double-sided is required
     * @return returns 0 if job is not acceptable, 1 if a printer is available and 
     * job is ready for printing, 2 if no printer is available and the job is waiting
     **/
    public int addJob(String cust,boolean staff,int custNo,int sides, int copies, boolean hQuality, boolean col, boolean dsided)
    {
        if((hQuality || col) && copies > 200) return 0;
        
        Job job = new Job(jobCounter, new Customer(cust, custNo, staff), sides, copies, hQuality, col, dsided);
        
        int jobIndex = allJobs.size();
        
        allJobs.add(job);
        
        jobCounter ++;
        
        Map.Entry<String, Printer> printerFound = findSuitablePrinter(job);
        
        if(printerFound != null) {
            printerFound.getValue().setState(Availability.INUSE);
            job.setAssignedPrinter(printerFound.getValue());
            job.setState(JobState.PRINTING);
            return  1;
        }
        
        return 2;
    }
    
    /**
     * Find a suitable printer for a job
     * @param job the job we want to find a printer for
     * @return key value pair of the suitable printer stored in this allPrinters, null is returned if nothing found
     */
    private Map.Entry<String, Printer> findSuitablePrinter(Job job) {
        Map.Entry<String, Printer> suitablePrinter = null;
        
        for(Map.Entry entry : allPrinters.entrySet()) {
            Printer p = (Printer)entry.getValue();
            if(job.isColour() == p.isColour() && job.isDsided() == p.isDoubleSided() &&
                    job.isQuality() == p.isHighQuality() && job.getCopies() < p.getMaxCopies())
            {
                suitablePrinter = entry;
                break;
            }
        }
        
        return suitablePrinter;
    }
    
    /** Provides a String representation of all jobs
     * @return returns a String representation of of all jobs
     **/
    public String getAllJobs()
    {
        StringBuilder sb = new StringBuilder();
        
        for(Job job : allJobs) {
            sb.append(job.toString() + System.getProperty("line.separator"));
        }
        
        return sb.toString();
    }
    
    /** Provides a String representation of all jobs which are 
     * still waiting for printing
     * @return returns a String representation of all jobs which are 
     * still waiting for printing
     **/
    public String getJobsWaiting()
    {
        StringBuilder sb = new StringBuilder();
        
        Job[] waitingJobs = getAllWaitingJobs();
        
        for(Job job: waitingJobs) {
            sb.append(job.toString() + System.getProperty("line.separator"));
        }
        
        return sb.toString();
    }
    
    /** Returns the cost of job specified by the parameter value once 
     * a job has been printed. If the job cannot be found or the 
     * printing has not been done return -1. Jobs printed on laser 
     * printers are charged at 10p per side per copy (whether using 
     * colour or not). Jobs printed on a bubblejet printer costs 6p 
     * per side per copy. Jobs printed on a plain printer cost 4p per 
     * side per copy, unless the printing is double-sided when the 
     * cost is 3p per side per copy. There is a set up cost of �1 
     * per job, with an additional single cost of �5 for colour, 
     * not dependent on the number of copies
     * @param jobNo is the number of the job
     * @return the cost of a job calculated as described above
     **/
    public double getJobCost(int jobNo)
    {
        Job job = findJob(jobNo);
        
        //No job found or not done yet
        if(job == null && job.getState() != 3) return -1;
        
        return job.getTotalCost();
    }
    
    //!!! THIS IS VERY AMBIGUOUS !!!
    //I was guessing if Job is Printing then set it to Done and printer to Avaliable
    /** records that the job specified by the parameter value has been 
     * done. If the job cannot be found return -1. If the job can be 
     * found,the state of the job is set to "done" ,the state of its
     * printer is set to "available" and 0 is returned.
     * @param jNo - the job number
     * @return 0 if job found and set to done, else -1
     **/
    public int setJobDone(int jNo)
    {
        Job job = findJob(jNo);
        
        //No job found or not done yet
        if(job == null || job.getState() != 1) return -1;
        
        job.setState(JobState.DONE);
        job.getAssignedPrinter().setState(Availability.FREE);
        
        return -1;
    }
    
    
    /** Checks the list of jobs and returns the job number of the first
     * job for which a printer is now available. If such a job is found,
     * the state of the job is set to "printing", the selected printer is
     * added to the job information and the printer's state is set to 
     * "in use"and the job number is returned. If there no such jobs,
     * return -1
     * @return the number of the first job which can now be done, else -1
     **/
    public int checkforJobsWaiting()
    {
        return getAllWaitingJobs().length;
    }


// ***************   file write/read  *********************
    /** Read printers from a specified comma-separated text file and store in a HashMap
     * @param filename is the name of the file from which data is read
     */
    public void readPrinters(String filename)
    {
        // read from text file
        String[][] loadedLines = loadLinesFromFile(filename);
        
        //Iterate through every line of text
        for(String[] line : loadedLines) {
            //Add them to allPrinters based on their type;
            switch(line[1].toLowerCase()) {
                case"laser":
                    allPrinters.put(line[0], new Laser(Integer.parseInt(line[2])));
                    break;
                case"bubblejet":
                    allPrinters.put(line[0], new BubbleJet(Boolean.parseBoolean(line[2])));
                    break;
                case"plain":
                    allPrinters.put(line[0], new Plain(Integer.parseInt(line[2]), line[3], line[4]));
                    break;
            }
        }
    }
    
    /** Write all jobs to specified file using object serialisation
     * @param fname is the name of the file to which data is written
     */
    public void writeJobsToFile(String fname)
    {   // uses object serialisation
        
        //Try to load file
        try {
            File outF = new File(Paths.get("/crashexam/Saves/" + fname).toString());
            
            if(!outF.exists()) {
                outF.createNewFile();
            }
            
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outF));
            out.writeObject(this.allJobs);
            out.close();
        } catch (IOException e) {
            //Handle Exception
        }
    }
    
    /** Read jobs from a file and store in an ArrayList using object serialisation
     * @param fname is the name of the file from which data is read
     */
    public void readJobsFromFile(String fname)
    {   // uses object serialisation
        //Try to load file
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(Paths.get("src/crashexam/Saves/" + fname).toString()));
            this.allJobs = (ArrayList<Job>)in.readObject();
            in.close();
        } catch (Exception e) {
            //Handle Exception
        }
    }
    
    // ***************   private local methods  *********************    
    
    /** Find a given job based on their job number
     *
     * @param jobNo the ID of the job to find
     * @return The job found or null
     */
    private Job findJob(int jobNo) {
        Job ret = null;
        for(Job job : allJobs) {
            if(job.getJobNo() == jobNo) {
                ret = job;
                break;
            }
        }
        return ret;
    }
    
    /** Get all waiting jobs
     *
     * @return all jobs that are waiting
     */
    private Job[] getAllWaitingJobs() {
        ArrayList<Job> waitingJobs = new ArrayList<Job>();
        
        for (Job job : allJobs) {
            if (job.getState() == JobState.WAITING.getJobState()) waitingJobs.add(job);
        }
        
        return waitingJobs.toArray(new Job[waitingJobs.size()]);
    }
    
    /** Loads each semicolon separated line from a file
     *
     * @param fileToLoad Name of the file to load
     * @return
     */
    private String[][] loadLinesFromFile(String fileToLoad) {
        
        LinkedList<String[]> strOut = new LinkedList<String[]>();
        
        //Start reading the file
        try(BufferedReader br = new BufferedReader(new FileReader(new File(Paths.get("src/crashexam/Resources/" + fileToLoad).toString())))){
            
            //Iterate through every line
            for(String line; (line = br.readLine()) != null; ) {
                
                //Ignore line its comment
                if(line.startsWith("#")) continue;
                
                //Check if line has ;
                if(line.indexOf(';') != -1) {
                    
                    //Split line based on ;
                    String[] data = line.split(";");
                    
                    //Remove whitespace in front and behind each string
                    for(int i = 0; data.length > i; i++) {
                        data[i] = data[i].trim();
                    }
                    
                    strOut.add(data);
                    
                } else {
                    //No ; found. Is this line not correct?
                    continue;
                }
            }
        } catch (IOException e) {
            //Handle Exception
            e.printStackTrace();
        }
        
        //Convert Linked list to jagged array
        
        return strOut.toArray(new String[strOut.size()][]);
    }
    
}



