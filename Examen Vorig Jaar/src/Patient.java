import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Patient extends Persoon {

    //Instantievariabelen (0.5 punten)
    private HashSet<Persoon> voogden;
    private HashMap<String,PlasKalender> plaskalenders; // plaskalender heeft als key de id van de plaskalender.

    //Methodes (4.5 punten)

    //Constructor.

    public Patient(String naam, String voornaam, String email, String woonplaats, LocalDate geboorteDatum, HashSet<Persoon> voogden, HashMap<String, PlasKalender> plaskalenders) {
        super(naam, voornaam, email, woonplaats, geboorteDatum);
        this.voogden = voogden;
        this.plaskalenders = plaskalenders;
    }

    //een methode addVoogd
    public void addVoogd(String naam, String voornaam, String email, String woonplaats, LocalDate geboorteDatum){
        Persoon voogd = new Persoon(naam,voornaam,email,woonplaats,geboorteDatum);
        this.voogden.add(voogd);
    }

    //een methode getVoogd
    public Persoon getVoogd(String email) throws PlasKalenderException {
        for(Persoon voogd : voogden)
            if(voogd.getEmail().equals(email))
                return voogd;
        throw new PlasKalenderException("Geen voogd van patient " + this.getNaam());
    }

    //een methode addPlasKalender
    public String addPlasKalender(){
        PlasKalender plasKalender = new PlasKalender(this.getNaam());
        plaskalenders.put(plasKalender.getId(),plasKalender);
        return plasKalender.getId();
    }

    //een methode getPlasKalender
    public PlasKalender getPlasKalender(String id) throws PlasKalenderException {
        for(PlasKalender plasKalender : plaskalenders.values()){
            if(plasKalender.getId().equals(id))
                return plasKalender;
        }
        throw new PlasKalenderException("Plaskalender " + id + " niet gevonden");
    }

    //een methode addPlasMoment
    public void addPlasMoment(String id, PlasMoment plasMoment) throws PlasKalenderException {
        for(PlasKalender plasKalender : plaskalenders.values()){
            if(plasKalender.getId().equals(id))
                plasKalender.addPlasMoment(plasMoment);
        }
        throw new PlasKalenderException("PlasMoment kan niet worden toegevoegd want kalender bestaat niet");
    }
}