@SuppressWarnings("serial")
public class KlantenKaartException extends Exception {
    public KlantenKaartException(String boodschap) {
        super("Fout Klantenkaart: " + boodschap);
    }
}