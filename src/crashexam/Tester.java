package crashexam;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester
{

    public static void main(String[] args)
    {
        Management dd = new Department("Welwyn","myprinters.txt");
        System.out.println("*********All Printers*******");
        System.out.println(dd.getAllPrinters());
        System.out.println(dd.addJob("ZZ",true,123,6,300,true,true,false));
        System.out.println(dd.addJob("AA",true,123,5,3,true,true,false));
        System.out.println(dd.addJob("XX",true,123,5,30,true,true,false));
        System.out.println(dd.addJob("ZZ",true,123,5,21,true,true,true));
        System.out.println(dd.addJob("BB",false,234,6,3,true,true,false));
        System.out.println(dd.addJob("CC",true,234,6,3,true,true,true));
        System.out.println(dd.addJob("CC",true,234,6,3,false,true,true));
        System.out.println(dd.addJob("CC",true,234,6,3,false,false,true));
        System.out.println(dd.toString());
        System.out.println("*********Jobs Waiting*******");
        System.out.println(dd.getJobsWaiting());
        dd.setJobDone(101); 
        dd.setJobDone(104);
        System.out.println("**********All Jobs******");
        System.out.println(dd.getAllJobs());
    }
}
