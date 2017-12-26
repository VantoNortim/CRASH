package crashexam.Job;
import crashexam.Customer;
import crashexam.Printers.*;

import java.io.*;
import java.util.*;

/** This class stores information about a Job including the customer, 
 * the number of sides and number of copies, whether highQuality, 
 * colour or double-sided printing is required and a unique job 
 * number allocated sequentially from 100.The job's state will be 
 * either: "waiting", "printing" or "done". In addition, 
 * information about a printer will be included when a printer 
 * is allocated for printing.
 * Current fields include: 
 * private Customer customer; 
 * private int sides, copies; 
 * private boolean colour; 
 * private int state; 
 * public static final int PRINTING = 1;
 * public static final int WAITING = 2;
 * public static final int DONE = 3;
 * This class is NOT COMPLETE ; fields, constructors and methods may need to be added.
 */
public class Job implements Serializable
{
    private int jobNo;
    private Customer customer;
    private int sides, copies; 
    private boolean quality; 
    private boolean colour; 
    private boolean dsided;
    private JobState state;

    /*
    Trash
    public static final int PRINTING = 1;
    public static final int WAITING = 2;
    public static final int DONE = 3;
    */

    private Printer assignedPrinter;

        /**
     * Constructor for Job; fields set from parameter values. Job number set by the system 
     * sequentially from 100 and printer initialised to null 
     */
    public Job(int jobNo, Customer customer, int sides, int copies, boolean quality, boolean colour, boolean dsided) {
        this.jobNo = jobNo;
        this.customer = customer;
        this.sides = sides;
        this.copies = copies;
        this.quality = quality;
        this.colour = colour;
        this.dsided = dsided;
        this.state = JobState.WAITING;
        this.assignedPrinter = null;
    }
    /**
     * returns the Job number (sequential from 100)
     * This one is really unclear and stupid design
     */
    public int getJobNo()
    {
        return jobNo;
    }
    
    /**
     * Returns details of the customer
     * @return details of the customer
     */
    public String getCustomerDetails()
    {
        return customer.toString();
    }
    
     /**
     * Returns the state of the Job: 1 - waiting, 2 - printing 3- done  
     */
    public int getState()
    {
        return state.getJobState();
    }
    
    public String getStateType()
    {
        String ret = "";
        switch (state) {
            case PRINTING:
                ret = "Printing";
                break;
            case WAITING:
                ret = "Waiting";
                break;
            case DONE:
                ret = "Done";
        }

        return ret;
    }
    /**
     * Returns the total cost of the job.There is a setup fee of �1 
     * together with a cost per side x number of sides x number of copies.An
     * additional charge of £5 is made for colour printing.
     */
    public double getTotalCost()
    {
        double pricePerSide = (assignedPrinter instanceof Plain && dsided) ? ((Plain)assignedPrinter).getPricePerSide(true) / 2 : assignedPrinter.getPricePerSide();
        return (colour ? 6 : 1) + ((dsided ? Math.ceil(sides / 2) : sides)* pricePerSide * copies);
    }
    
    public void setState(JobState state) {
        this.state = state;
    }
    
    public void setAssignedPrinter(Printer assignedPrinter) {
        this.assignedPrinter = assignedPrinter;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public int getSides() {
        return sides;
    }
    
    public int getCopies() {
        return copies;
    }
    
    public boolean isQuality() {
        return quality;
    }
    
    public boolean isColour() {
        return colour;
    }
    
    public boolean isDsided() {
        return dsided;
    }
    
    public Printer getAssignedPrinter() {
        return assignedPrinter;
    }
    
    @Override
    public String toString() {
        return "Job{" +
                "jobNo=" + jobNo +
                ", customer=" + customer +
                ", sides=" + sides +
                ", copies=" + copies +
                ", quality=" + quality +
                ", colour=" + colour +
                ", dsided=" + dsided +
                ", state=" + state +
                ", assignedPrinter=" + assignedPrinter +
                '}';
    }
}
