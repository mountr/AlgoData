import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class PlasKalender {

    //Instantievariabelen (0.5 punten)
    private ArrayList<PlasMoment> plasMomenten;
    private String id;
    private static int counter;

    //Methodes (6.5 punten)

    //Constructor.
    public PlasKalender(String naamPatient){
        this.id = naamPatient + counter;
        this.plasMomenten = new ArrayList<>();
        counter++;
    }

    //de methode addPlasMoment
    public void addPlasMoment(PlasMoment plasMoment){
        plasMomenten.add(plasMoment);
    }

    //de methode sort
    public void sort(){
        for(int i = 0; i < plasMomenten.size() - 1; i++) {
            if (plasMomenten.get(i).compareTo(plasMomenten.get(i + 1)) > 0) { // ALS het plasmoment op plaats i+1 eerder is dan moment op i
                PlasMoment eerder = plasMomenten.get(i + 1);
                PlasMoment later = plasMomenten.get(i);
                plasMomenten.add(i, eerder);
                plasMomenten.add(i + 1, later);
            }
        }
    }

    //de methode isValid1
    public boolean isValid1(){
        this.sort();
        LocalDate startDag = getPlasMomenten().get(0).getDatum();
        LocalDate eindDag30Dagen = startDag.plusDays(29); // Periode van 30 dagen die start op dag 1 (startdag) dus er maar 29 meer bij
        int amountOfDaysInPeriod = 0;
        for(PlasMoment moment : getPlasMomenten())
            if(moment.getDatum().isBefore(eindDag30Dagen) &&
                    (moment.getDatum().isEqual(startDag) || moment.getDatum().isAfter(startDag)))
                amountOfDaysInPeriod++;
        if (amountOfDaysInPeriod < 10)
            return false;
        return true;
    }

    //de methode isValid2
    public boolean isValid2(){
        int count = 0;
        for( int i = 1; i<getPlasMomenten().size(); i++){
            LocalDate nextDayDate = getPlasMomenten().get(i-1).getDatum().plusDays(1);
            if(nextDayDate.equals(getPlasMomenten().get(i).getDatum()))
                count++;
            else
                count = 0;
        }
        if(count >= 10)
            return true;
        return false;

    }

    //de methode toString
    @Override
    public String toString() {
        String output = "";
        for(PlasMoment moment : getPlasMomenten())
            output += "\n" + moment.getNacht() + ";" + moment.getRegistratie().getEmail();
        // Was eerst moment.getRegistratie() maar prof zei dat moment.getRegistratie().getEmail() ook mocht
        return output;
    }

    // Getters
    public ArrayList<PlasMoment> getPlasMomenten() {
        return plasMomenten;
    }

    public String getId() {
        return id;
    }
}