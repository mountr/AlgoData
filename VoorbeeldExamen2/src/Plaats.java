public class Plaats {

    // Inst Var
    private String naam;
    private String adres;
    private String stad;
    private String postcode;

    //Constructor
    public Plaats(String naam, String adres, String stad, String postcode) {
        this.naam = naam;
        this.adres = adres;
        this.stad = stad;
        this.postcode = postcode;
    }
    //Getter

    public String getNaam() {
        return naam;
    }

    // Hashcode & Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plaats plaats = (Plaats) o;

        return naam.equals(plaats.naam);
    }

    @Override
    public int hashCode() {
        return naam.hashCode();
    }
}
