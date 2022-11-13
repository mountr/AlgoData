import java.time.LocalDate;

//gegeven

public class Persoon {

    private String naam;
    private String voornaam;
    private String email;
    private String woonplaats;
    private LocalDate geboorteDatum;


    public Persoon(String naam, String voornaam, String email, String woonplaats, LocalDate geboorteDatum) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.email = email;
        this.woonplaats = woonplaats;
        this.geboorteDatum = geboorteDatum;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persoon persoon = (Persoon) o;

        if (naam != null ? !naam.equals(persoon.naam) : persoon.naam != null)
            return false;
        if (voornaam != null ? !voornaam.equals(persoon.voornaam) : persoon.voornaam != null)
            return false;
        return email != null ? email.equals(persoon.email) : persoon.email == null;
    }

    @Override
    public int hashCode() {
        int result = naam != null ? naam.hashCode() : 0;
        result = 31 * result + (voornaam != null ? voornaam.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

}