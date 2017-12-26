package crashexam.Printers;

/**
 * Class stores information about a Laser copier including 
 * its departmental id, maximum number of copies = 200, 
 * cost per copy per page (currently 6p)and whether it can do 
 * remote printing(default is false)and replacement cost. 
 * Class is NOT COMPLETE
 * - additional fields & methods may be required
 */
public class Laser extends Printer
{
    private int repairCost;
    private boolean remote;
    
    /** Constructor for a laser copier
     * @param depId is the departmental identifier
     * @param replace is the replacement value
     */

    public Laser(int repairCost) {
        super(true, true, true, 200, 10);
        this.repairCost = repairCost;
        this.remote = false;
    }

    /**
     * sets remote printing to true (default is false) 
     */
    public void setRemote()
    {
        this.remote = true;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public boolean isRemote() {
        return remote;
    }

    @Override
    public String toString() {
        return "Laser{" +
                "repairCost=" + repairCost +
                ", remote=" + remote +
                super.toString() + " }";
    }
}
