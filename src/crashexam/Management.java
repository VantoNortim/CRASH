package crashexam;
/**
 * This interface specifies the behaviour expected from the CRASH
 * system as required for 6COM1037 Object Oriented Design Exam - Jan 2018
 * 
 * @author A.A.Marczyk 
 * @version 15/10/17
 */
public interface Management
{
    
    /**Returns a String representation of the department,including
     * its location and all of its printers
     * @return a String representation of the department,including
     * its location and all its printers
     **/
    public String toString();
        
 // ***************** Printers ************************   
    /** Returns a String representation of all the printers  
     * @return returns a String representation of all printers 
     **/
    public String getAllPrinters();
       
    /** Returns true if the printer with the departmental identifier 
     * can be found in the system, false otherwise.
     * @param depId represents the departmental identifier of the printer
     * @return returns true if the printer with the departmental identifier 
     * can be found, false otherwise.
     **/
    public boolean isPrinter(String depId);
    
    /** Removes a printer from the department.
     * pre-condition: isPrinter(depId)
     * @param depId represents the departmental identifier of the printer
     **/
    public void removePrinter(String depId);
    
    
//************************************************************** 
    /** Adds a job to the job list if acceptable.Jobs requiring high quality or 
     * colour printing of more than 200 copies are not acceptable, and 0 is returned.
     * Jobs which are acceptable are numbered sequentially from 100 and added to the 
     * list.If a suitable printer is available,the state of the job is set to 
     * "printing",the selected printer is added to the job, the printer's state is 
     * set to "in use" and 1 is returned. If a printer is not available, job's state
     * is set to "waiting"and 1 is returned.
     * @cust is the name of the customer
     * @staff indicates whether the customer is a member of staff
     * @custNo is either staff number, if staff, or student number,if student
     * @sides is the number of sides in the document
     * @copies is the number of copies required
     * @param col indicates whether colour is required
     * @param hQuality indicates whether high quality is required
     * @param dsided indicates whether double-sided is required
     * @return returns 0 if job is not acceptable, 1 if a printer is available and 
     * job is ready for printing, 2 if no printer is available and the job is waiting
     **/ 
     public int addJob(String cust,boolean staff,int custNo,int sides,
           int copies, boolean hQuality, boolean col, boolean dsided);

           
    /** Provides a String representation of all jobs 
     * @return returns a String representation of all jobs
     **/
    public String getAllJobs();
    
    /** Provides a String representation of all jobs which are 
     * still waiting for printing
     * @return returns a String representation of all jobs which are 
     * still waiting for printing
     **/
    public String getJobsWaiting();

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
    public double getJobCost(int jNo);
 
    
    /** records that the job specified by the parameter value has been 
     * done. If the job cannot be found return -1. If the job can be 
     * found,the state of the job is set to "done" ,the state of its
     * printer is set to "available" and 0 is returned.
     * @param jNo - the job number
     * @return 0 if job found and set to done, else -1
     **/
    public int setJobDone(int jNo);
    
    
    /** Checks the list of jobs and returns the job number of the first
     * job for which a printer is now available. If such a job is found,
     * the state of the job is set to "printing", the selected printer is
     * added to the job information and the printer's state is set to 
     * "in use"and the job number is returned. If there no such jobs,
     * return -1
     * @return the number of the first job which can now be done, else -1
     **/
    public int checkforJobsWaiting();  
 
// ***************   file write/read  *********************
   /** Read printers from a specified comma-separated text file and store in a HashMap
     * @param fname is the name of the file from which data is read
     */
    public void readPrinters(String filename);

    /** Write all jobs to specified file using object serialisation
     * @param fname is the name of the file to which data is written
     */
    public void writeJobsToFile(String fname);
    
    /** Read jobs from a file and store in an ArrayList using object serialisation
     * @param fname is the name of the file from which data is read
     */
    public void readJobsFromFile(String fname);
    
}





