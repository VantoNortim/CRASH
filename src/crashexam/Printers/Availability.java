package crashexam.Printers;
/**
 * Enumeration class Availability - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Availability
{
    FREE(" Free"), INUSE(" In use"),UNAVAILABLE(" Unavailable");
    private String state;
    
    private Availability(String st)
    {
        state = st;
    }
    
    public String toString()
    {
        return state;
    }
}
