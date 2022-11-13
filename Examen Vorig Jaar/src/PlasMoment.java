import java.time.LocalDate;

public class PlasMoment implements Comparable<PlasMoment> {

    enum Nacht { drogeNacht, druppels, natteNacht }

    //Instantievariabelen (1 punt)
    private LocalDate datum;
    private Persoon registratie;
    private Nacht nacht;

    //Methodes (2 punten)

    // Constructor
    public PlasMoment(Nacht nacht, Persoon persoon){
        this.nacht = nacht;
        this.registratie = persoon;
        this.datum = LocalDate.now();
    }

    // compareTo om 2 plasmomenten in tijd met elkaar te vergelijken
    @Override
    public int compareTo(PlasMoment o) {
        // o = plasmoment met de latere datum
        if(datum.isBefore(o.datum))
            return -1;
        // o = plasmoment met de eerdere datum
        if(datum.isAfter(o.datum))
            return +1;
        // objecten hebben zelfde datum
        return 0;
    }

    // Getters
    public LocalDate getDatum() {
        return datum;
    }
    public Nacht getNacht() {
        return nacht;
    }
    public Persoon getRegistratie() {
        return registratie;
    }
}