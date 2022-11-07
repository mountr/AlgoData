import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class Winkel implements Serializable {

    //Instantievariabelen

    private static final long serialVersionUID = 9674;
    private String winkelID;
    private String naam;
    private double aantalPuntenPerEuro;
    private String beloningTekst;

    //Constructor

    public Winkel(String winkelID, String naam, double aantalPuntenPerEuro, String beloningTekst) {
        this.winkelID = winkelID;
        this.naam = naam;
        this.aantalPuntenPerEuro = aantalPuntenPerEuro;
        this.beloningTekst = beloningTekst;
    }

    //Accessors

    public String getWinkelID() {
        return winkelID;
    }

    public String getNaam() {
        return naam;
    }

    public double getAantalPuntenPerEuro() {
        return aantalPuntenPerEuro;
    }

    public String getBeloningTekst() {
        return beloningTekst;
    }

    // hashcode en equals


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winkel winkel = (Winkel) o;
        return Double.compare(winkel.aantalPuntenPerEuro, aantalPuntenPerEuro) == 0 && winkelID.equals(winkel.winkelID) && naam.equals(winkel.naam) && beloningTekst.equals(winkel.beloningTekst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winkelID);
    }

    @Override
    public String toString() {
        return naam + " (" + winkelID + ") geeft " + aantalPuntenPerEuro + " punten per euro";
    }
}
