import java.io.*;
import java.util.HashMap;

public class WinkelApp implements IWinkelApp{

    //Instantievariabelen

    private HashMap<String,KlantenKaart> klanten;
    private Winkel winkel;
    private KlantenKaart aangemeldeKlant;

    //Constructors
    public WinkelApp() {
        klanten = new HashMap<>();
    }

    public WinkelApp(String klantenBestand, String winkelBestand) throws KlantenKaartException{
        laadWinkel(winkelBestand);
        laadKlanten(klantenBestand);
    }

    @Override
    public int laadKlanten(String klantenBestand) throws KlantenKaartException {
        try {
            FileInputStream fis = new FileInputStream(klantenBestand);
            ObjectInputStream ois = new ObjectInputStream(fis);
            klanten = (HashMap<String, KlantenKaart>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Klanten gelezen van bestand " + klantenBestand);
            return klanten.keySet().size();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new KlantenKaartException("Fout bij lezen van bestand " + klantenBestand);
        }
    }

    @Override
    public void laadWinkel(String winkelBestand) throws KlantenKaartException {
        try {
            FileInputStream fis = new FileInputStream(winkelBestand);
            ObjectInputStream ois = new ObjectInputStream(fis);
            winkel = (Winkel) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Winkel gelezen van bestand " + winkelBestand);
        } catch (IOException | ClassNotFoundException e) {
            throw new KlantenKaartException("Fout bij lezen van bestand " + winkelBestand);
        }
    }

    //2 eigen methodes omdat hier vaak naar gevraagd wordt in de andere methodes
    public void kaartValid(KlantenKaart kaart) throws KlantenKaartException {
        if (kaart == null)
            throw new KlantenKaartException("Geen klant aangemeld!");
    }
    public void winkelValid(Winkel winkel) throws KlantenKaartException {
        if (winkel == null)
            throw new KlantenKaartException("Geen winkel ingeladen!");
    }

    public int getAantalPunten() throws KlantenKaartException {
        kaartValid(aangemeldeKlant);
        winkelValid(winkel);
        if(aangemeldeKlant.getVerzameldePuntenPerWinkel().get(winkel) == null)
            return 0;
        return aangemeldeKlant.getVerzameldePuntenPerWinkel().get(winkel);
    }

    @Override
    public String aanmeldenKlant(String id) throws KlantenKaartException {
        KlantenKaart kaart = null;
        for (String s : klanten.keySet())
            if (s.equals(id))
                kaart = klanten.get(s);
        winkelValid(winkel);
        if (kaart == null)
            throw new KlantenKaartException("Klant '" + id + " niet gevonden!");
        aangemeldeKlant = kaart;
        return "Welkom " + kaart.getVoornaam() + " " + kaart.getNaam() + "\n"
                + "Aantal punten verzameld: " + getAantalPunten() + "\n" + winkel.getBeloningTekst();
    }

    @Override
    public String registrerenTransactie(double bedrag) throws KlantenKaartException {
        kaartValid(aangemeldeKlant);
        winkelValid(winkel);
        aangemeldeKlant.toevoegenTransactie(winkel, bedrag, (int) (winkel.getAantalPuntenPerEuro() * bedrag));
        return aangemeldeKlant.getVoornaam() + " " + aangemeldeKlant.getNaam() + ", je transactie werd geregistreerd!!!\n"
                + "Aantal punten verzameld: " + getAantalPunten();
    }

    @Override
    public String registrerenBeloning(int aantalPunten) throws KlantenKaartException {
        kaartValid(aangemeldeKlant);
        winkelValid(winkel);
        if (aangemeldeKlant.getVerzameldePuntenPerWinkel().get(winkel) != null) {
            if (aangemeldeKlant.getVerzameldePuntenPerWinkel().get(winkel) < aantalPunten)
                throw new KlantenKaartException("U heeft slechts " + aangemeldeKlant.getVerzameldePuntenPerWinkel().get(winkel) + " punten beschikbaar!!");
            else {
                aangemeldeKlant.getVerzameldePuntenPerWinkel().put(winkel, getAantalPunten() - aantalPunten);
                return aangemeldeKlant.getVoornaam() + " " + aangemeldeKlant.getNaam() + ", je beloning werd geregistreerd!!!\n"
                        + "Aantal punten verzameld: " + getAantalPunten();
            }
        }
        throw new KlantenKaartException("U heeft slechts 0 punten beschikbaar!!");
    }

    @Override
    public String getEmailAdressenKlanten() {
        String emails = "";
        if (klanten == null)
            return emails;
        for (KlantenKaart k : klanten.values())
            if (k.getVerzameldePuntenPerWinkel().get(winkel) != null)
                emails = emails.concat(k.getEmail() + ";");
        if (emails.equals(""))
            return emails;
        emails = emails.substring(0, emails.length() - 1);
        return emails;
    }
    @Override
    public int bewaarKlanten(String klantenBestand) throws KlantenKaartException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(klantenBestand));
            oos.writeObject(klanten);
        }
        catch (IOException e) {
            throw new KlantenKaartException("Fout bij schrijven naar bestand " + klantenBestand);
        }
        return klanten.size();
    }
    @Override
    public void bewaarWinkel(String winkelBestand) throws KlantenKaartException {
        winkelValid(winkel);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(winkelBestand));
            oos.writeObject(winkel);
            oos.close();
            System.out.println("Winkel weggeschreven naar bestand " + winkelBestand);
        } catch (IOException e) {
            throw new KlantenKaartException("Fout bij schrijven naar bestand " + winkelBestand);
        }
    }
    @Override
    public String printOverzichtGespaardePunten(String klantID) throws KlantenKaartException {
        if (!klanten.containsKey(klantID))
            throw new KlantenKaartException("Klant '" + klantID + "' niet gevonden!");
        else {
            KlantenKaart k = klanten.get(klantID);
            String s = "";
            for(Winkel w : k.getVerzameldePuntenPerWinkel().keySet())
                if(k.getVerzameldePuntenPerWinkel().get(w) != null)
                    s = s.concat(w.getNaam()+": "+k.getVerzameldePuntenPerWinkel().get(w)+" punten\n");
            if(s.equals(""))
                return k.getVoornaam()+" "+k.getNaam()+" heeft nog geen punten verzameld";
            else{
                String overzicht = "Overzicht voor" +k.getVoornaam()+" "+k.getNaam()+"\n-------------\n";
                overzicht = overzicht.concat(s);
                return overzicht;
            }
        }
    }
}
