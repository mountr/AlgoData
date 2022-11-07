import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class KlantenKaart implements Serializable {

    //Instantievariabelen
    private static final long serialVersionUID = 552;
    private String klantID;
    private String voornaam;
    private String naam;
    private String email;
    private ArrayList<Transactie> transacties;
    private HashMap<Winkel, Integer> verzameldePuntenPerWinkel;

    public KlantenKaart(String klantID, String voornaam, String naam, String email) {
        this.klantID = klantID;
        this.voornaam = voornaam;
        this.naam = naam;
        this.email = email;
        transacties = new ArrayList<>();
        verzameldePuntenPerWinkel = new HashMap<>();
    }

    //Accessors
    public String getKlantID() {
        return klantID;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Transactie> getTransacties() {
        return transacties;
    }

    public HashMap<Winkel, Integer> getVerzameldePuntenPerWinkel() {
        return verzameldePuntenPerWinkel;
    }

    //Methodes
    public void toevoegenTransactie (Winkel winkel, double bedrag, int punten){
        transacties.add(new Transactie(winkel.getWinkelID(),bedrag, punten));
        boolean flag = false;
        for(Winkel winkelGeselecteerd : verzameldePuntenPerWinkel.keySet())
            if(winkelGeselecteerd.equals(winkel)){
                verzameldePuntenPerWinkel.put(winkel,verzameldePuntenPerWinkel.get(winkelGeselecteerd) + punten);
                flag = true;
            }
        if(!flag)
            verzameldePuntenPerWinkel.put(winkel,punten);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KlantenKaart that = (KlantenKaart) o;

        if (!klantID.equals(that.klantID)) return false;
        if (!voornaam.equals(that.voornaam)) return false;
        if (!naam.equals(that.naam)) return false;
        if (!email.equals(that.email)) return false;
        if (!transacties.equals(that.transacties)) return false;
        return verzameldePuntenPerWinkel.equals(that.verzameldePuntenPerWinkel);
    }

    @Override
    public int hashCode() {
        int result = klantID.hashCode();
        result = 31 * result + voornaam.hashCode();
        result = 31 * result + naam.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + transacties.hashCode();
        result = 31 * result + verzameldePuntenPerWinkel.hashCode();
        return result;
    }

    public String toString(){
        String finalString = "";
        int verzameldepunten = 0;
        for(Integer integer : verzameldePuntenPerWinkel.values())
            verzameldepunten += integer;
        finalString +="Klantenkaart van "+voornaam+" "+naam+" ("+klantID+"): "+transacties.size()
                +" transactie(s) in"+verzameldePuntenPerWinkel.keySet().size()+" winkel(s) en een totaal van "
                +verzameldepunten+" beschikbare punten";
        return finalString;
    }
}
