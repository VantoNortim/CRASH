package crashexam.Printers;
/**
 * Class stores information about a bubbleJet printer including
 * its departmental id, maximum number of pages = 150, cost
 * per copy per page (currently 10p) and whether it can do
 * photo printing(default is false).
 * Class is incomplete - additional fields & methods required
 */
public class BubbleJet extends Printer
{
    // fields to be decided
    private boolean photo;

    public BubbleJet(boolean photo) {
        super(true, true, false, 100, 0.06);
        this.photo = photo;
    }

    /**
     * returns true if copier can do photo quality, else false 
     */
    public boolean getPhoto()
    {
        return photo;
    }

    @Override
    public String toString() {
        return "BubbleJet{" +
                "photo=" + photo +
                super.toString() + " }";
    }
}

    
    
        