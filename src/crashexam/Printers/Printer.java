package crashexam.Printers;

import java.io.Serializable;

public abstract class Printer implements Serializable {
    //Printer
    private Availability state;

    protected final boolean HighQuality;
    protected final boolean Colour;
    protected final boolean DoubleSided;
    protected final int MaxCopies;

    //Double for price £1 == 1.00, 1p = 0.01
    protected final double pricePerSide;

    public Printer(boolean highQuality, boolean colour, boolean doubleSided, int maxCopies, double pricePerSide) {
        this.state = Availability.FREE;
        HighQuality = highQuality;
        Colour = colour;
        DoubleSided = doubleSided;
        this.MaxCopies = maxCopies;

        this.pricePerSide = pricePerSide;
    }

    public Availability getState() {
        return state;
    }

    public void setState(Availability state) {
        this.state = state;
    }

    public boolean isHighQuality() {
        return HighQuality;
    }

    public boolean isColour() {
        return Colour;
    }

    public boolean isDoubleSided() {
        return DoubleSided;
    }
    
    public int getMaxCopies() {
        return MaxCopies;
    }

    public double getPricePerSide() {
        return pricePerSide;
    }

    @Override
    public String toString() {
        return " Printer{" +
                "state=" + state +
                ", HighQuality=" + HighQuality +
                ", Colour=" + Colour +
                ", DoubleSided=" + DoubleSided +
                ", MaxCopies=" + MaxCopies +
                ", pricePerSide=" + pricePerSide +
                '}';
    }
}