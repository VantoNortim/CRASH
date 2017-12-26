package crashexam;
/**
 * This class stores information about customers.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    private String name;
    private int number;
    private boolean isStaff;
    
    /**Constructor requires the customer name, either an employee 
     * or a student number depending on whether customer is 
     * staff or not
     * @param nm name of the customer
     * @param num either an employee or a student number
     * @param stf true if customer is staff, false if a student
     */
    public Customer(String nm, int num, boolean stf)
    {
       this.name = nm;
       this.number = num;
       this.isStaff = stf;
    }
    
    public boolean isStaff()
    {
        return isStaff;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", isStaff=" + isStaff +
                '}';
    }
}
