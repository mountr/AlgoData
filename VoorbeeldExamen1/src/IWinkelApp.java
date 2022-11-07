public interface IWinkelApp {
    public int laadKlanten(String klantenBestand) throws KlantenKaartException;
    public void laadWinkel(String winkelBestand)throws KlantenKaartException;
    public int getAantalPunten() throws KlantenKaartException;
    public String aanmeldenKlant(String id) throws KlantenKaartException;
    public String registrerenTransactie(double bedrag) throws KlantenKaartException;
    public String registrerenBeloning(int aantalPunten) throws KlantenKaartException;
    public String getEmailAdressenKlanten();
    public int bewaarKlanten(String klantenBestand) throws KlantenKaartException;
    public void bewaarWinkel(String winkelBestand) throws KlantenKaartException;
    public String printOverzichtGespaardePunten(String klantID) throws KlantenKaartException;
}