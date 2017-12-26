package crashexam;
import java.io.*;
/**
 * Provide a command line interface for the job submission system.
 * Only shows a selection of possible functions: add job, list jobs waiting
 * record job done.
 * 
 * @author A.A.Marczyk
 * @version 20/10/17
 */
public class JobUI
{
    private Management dd = new Department("College Lane", "myprinters.txt");
    private BufferedReader myIn = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
        new JobUI().run();
    }
    
    private void run()
    {
        int choice;
        try
        {
            choice = -1;
            while (choice != 0)
            {
                choice = getMenuItem();
                if (choice == 1)  { System.out.println(dd.getAllJobs());}
                else if (choice == 2){doAddJob();}
                else if (choice == 3){System.out.println(dd.getJobsWaiting());}
                else if(choice == 4){ doJobDone();}   
            }
        }
        catch (JobNotFoundException e) {System.out.println (e);}
        catch (IOException e) {System.out.println (e);}
        System.out.println("Thank-you");
    }
    
    private int getMenuItem()throws IOException
    {   int choice = -1;  
        System.out.println("Main Menu");
        System.out.println("0. quit");
        System.out.println("1. list all jobs");
        System.out.println("2. add a job");
        System.out.println("3. list jobs waiting");
        System.out.println("4. record job done"); 
        while (choice < 0 || choice  >4 )
        {
            System.out.println("Enter the number of your choice");
            choice =  Integer.parseInt(myIn.readLine());
        }
        return choice;        
    }  
    
    private void doJobDone()throws IOException, JobNotFoundException
    {
        System.out.println("Enter Job number");
        String s = (myIn.readLine()).toLowerCase().trim();
        int jj = Integer.parseInt(s);
        int res = dd.setJobDone(jj);
        if (res == 0)
        {
            System.out.println("Job done confirmed");   
        }
        else
        {
            throw new JobNotFoundException();
        }                                        
    }
    
    private void doAddJob() throws IOException
    {   
        int copies, code, pages;
        boolean st,c,h,d;
        String cust,s;
        System.out.println ("Enter customer name");
        cust = myIn.readLine();
        System.out.println("Is customer staff ? y/n");
        s = (myIn.readLine()).toLowerCase().trim();
        st = s.contains("y");
        if (st)
        {
            System.out.println ("Enter department number");
        }
        else
        {
            System.out.println ("Enter student number");
        }
        s = (myIn.readLine()).toLowerCase().trim();
        code = Integer.parseInt(s); 
        System.out.println ("Pages in document ?");
        s = (myIn.readLine()).toLowerCase().trim();
        pages = Integer.parseInt(s);
        System.out.println ("Copies required ?");
        s = (myIn.readLine()).toLowerCase().trim();
        copies = Integer.parseInt(s);
        System.out.println("Colour  ? y/n");
        s = myIn.readLine().toLowerCase().trim();
        c = s.contains("y");
        System.out.println("High Quality  ? y/n");
        s = myIn.readLine().toLowerCase().trim();
        h = s.contains("y");
        System.out.println("Double sided  ? y/no");
        s = myIn.readLine().toLowerCase().trim();
        d = s.contains("y");
        System.out.println(dd.addJob(cust,st,code,pages,copies,c,h,d));
    }
    
    private class JobNotFoundException extends Exception
    {
        public JobNotFoundException()
        {
            super ("Not possible as job not found" );
        }
    }
}
