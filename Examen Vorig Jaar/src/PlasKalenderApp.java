import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PlasKalenderApp {
    //Instantievariabelen
    private ArrayList<Patient> patienten;

    //Methodes (5 punten)

    //Constructor
    public PlasKalenderApp(){
        patienten = new ArrayList<>();
    }

    //de methode toevoegenPatient
    public void toevoegenPatient(String naam, String voornaam, String email, String woonplaats, LocalDate geboortedatum){
        Patient patient = new Patient(naam,voornaam,email,woonplaats,geboortedatum, new HashSet<Persoon>(), new HashMap<>());
        patienten.add(patient);
    }

    //de methode getPatient
    public Patient getPatient(String email) throws PlasKalenderException {
        for(Patient patient : patienten)
            if(patient.getEmail().equals(email))
                return patient;
        throw new PlasKalenderException("Patient niet gevonden");
    }

    //de methode toevoegenVoogd
    public void toevoegenVoogd(String emailPatient, String naam, String voornaam, String emailVoogd, String woonplaats, LocalDate geboortedatum) throws PlasKalenderException {
        getPatient(emailPatient).addVoogd(naam,voornaam,emailVoogd,woonplaats,geboortedatum);
    }

    //de methode addPlaskalender
    public String addPlasKalender(String email) throws PlasKalenderException {
        Patient patient = getPatient(email);
        PlasKalender plasKalender = new PlasKalender(patient.getNaam());
        return plasKalender.getId();
    }

    //de methode addPlasMoment
    public void addPlasMoment(String emailToevoeger, String id, String emailPatient, PlasMoment.Nacht nacht) throws PlasKalenderException {
        Patient patient = getPatient(emailPatient);
        Persoon voogd = null;
        if(!emailToevoeger.equals(emailPatient))
            voogd = patient.getVoogd(emailToevoeger);
        if(emailToevoeger.equals(emailPatient) || emailToevoeger.equals(voogd.getEmail())) {
            PlasMoment moment = new PlasMoment(nacht, patient);
            patient.getPlasKalender(emailPatient).addPlasMoment(moment);
        }
    }

    //de methode plasKalenderValid
    public boolean plasKalenderValid(String email, String id) throws PlasKalenderException {
        Patient patient = getPatient(email);
        PlasKalender plasKalender = patient.getPlasKalender(id);
        return plasKalender.isValid1() || plasKalender.isValid2();
    }

    //de methode exportPlasKalender
    public void exportPlasKalender(String email, String id , String bestandsnaam) throws PlasKalenderException {
        try {
            Patient patient = getPatient(email);
            PlasKalender plasKalender = patient.getPlasKalender(id);
            File file = new File(bestandsnaam);
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(plasKalender.toString());
            stream.close();
        } catch (IOException e) {
            throw new PlasKalenderException("Fout bij schrijven naar csv bestand");
        }
    }
}