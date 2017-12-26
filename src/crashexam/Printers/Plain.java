package crashexam.Printers;

/**
 * First draft of a class to store information about a plain paper printer
 * including its departmental id, maximum number of copies, and cost per 
 * copy per side (currently 3p if double-sided, otherwise 4p) 
 * Current fields: 
 *	private String id; 
 *	private Availability state; 
 *	private int maxCopies;
 *	private String make; 
 *	private String contact = ""; 
 * NOT the final version 
 */

public class Plain extends Printer
{
    private String make; 
    private String contact;

    private final double pricePerDoubleSide = 0.03;

    /** Constructor which requires the departmental id, the maximum
     * total number of sides in one job allowed for this printer and the make. 
     * //@param ID is the departmental id
     * @param maxCopies is the maximum number of copies allowed in one job
     * @param make is the printer make
     * @param contact is the nme of the contact person
     */
    public Plain(int maxCopies, String make, String contact) {
        super(false, false, true, maxCopies, 0.04);
        this.make = make;
        this.contact = contact;
    }
    
    /**
     * Returns true if the number of copies required is less than
     * the maximum allowed and if neither high quality nor colour
     * is required 
     * @param copies number of copies required
     * @param quality true if high quality is required, else false
     * @param colour true if colour printing is required, else false
     * @param dsided true if double-sided printing is required, else false
     * @return true if the number of copies required is less than
     * the maximum allowed and if neither high quality nor colour
     * is required, else false
     */
    public boolean acceptSelection(int copies, boolean quality, boolean colour, boolean dsided)
    {
         return copies < MaxCopies && !quality && !colour;
    }
    
    /**
     * change contact details for repair technician from parameter values 
     * @param details of the repair technician
     */
    public void changeContact(String details)
    {
        this.contact = details;
    }
    
    /** 
     * Returns contact details for repair technician
     * @return contact details for repair technician
     */
    public String getContact()
    {
        return contact;
    }
    
    
    /**
     * Returns cost per copy per side : 3p if double-sided, otherwise 4p
     * @param dsided true if double-sided printing is required, else false
     * @return cost per copy per side : 3p if double-sided, otherwise 4p
     */
    public double getPricePerSide(boolean dsided)
    {
        return dsided ? pricePerDoubleSide : pricePerSide;
    }
    
    //Overriden overload to prevent improper use from super
    @Override
    public double getPricePerSide() {
        return getPricePerSide(false);
    }
    
    /**
     * Returns printer's make
     * @return printer's make
     */
    public String getMake()
    {
        return make;
    }
    
    /**
     * Returns the maximum number of copies allowed in one job
     * @return the maximum number of copies allowed in one job
     */
    public int getMaxCopies() { return MaxCopies; }

    /**
     * returns a String representation of the printer 
     * @return a String representation of the printer 
     */
    @Override
    public String toString() {
        return "Plain{" +
                "make='" + make + '\'' +
                ", contact='" + contact + '\'' +
                ", pricePerSide=" + pricePerSide +
                ", pricePerDoubleSide=" + pricePerDoubleSide +
                super.toString() + " }";
    }
}
