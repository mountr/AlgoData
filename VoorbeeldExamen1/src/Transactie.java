import java.io.Serializable;

public class Transactie implements Serializable {

    //Instantievariabelen

    private static final long serialVersionUID = 113;
    private String winkelID;
    private double bedrag;
    private int punten;
    private Date datum;

    public Transactie(String winkelID, double bedrag, int punten) {
        this.winkelID = winkelID;
        this.bedrag = bedrag;
        this.punten = punten;
        this.datum = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transactie that = (Transactie) o;

        if (Double.compare(that.bedrag, bedrag) != 0) return false;
        if (punten != that.punten) return false;
        if (!winkelID.equals(that.winkelID)) return false;
        return datum.equals(that.datum);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = winkelID.hashCode();
        temp = Double.doubleToLongBits(bedrag);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + punten;
        result = 31 * result + datum.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Aankoop op " + datum + " van " + bedrag + " (" + punten +" punten) in " + winkelID;
    }
}