import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TSP {

    //Instantie Variabelen
    private Plaats startPlaats;
    private Plaats eindPlaats;
    private ArrayList<Plaats> plaatsen;
    private double[][] afstanden;

    //Constructor
    public TSP(String plaatsenBestand, String afstandenBestand, String startplaats, String eindplaats) {
        leesPlaatsen(new File(plaatsenBestand));
        leesAfstanden(new File(afstandenBestand));

        int flagStart = 0;
        int flagEind = 0;

        for (Plaats plaats : plaatsen) {
            if (plaats.getNaam().equals(startplaats)) {
                startPlaats = plaats;
                flagStart =1;
            }

        }
        for (Plaats plaats : plaatsen) {
            if (plaats.getNaam().equals(eindplaats)) {
                eindPlaats = plaats;
                flagEind = 1;
            }
        }
        if (flagEind == 0 && flagStart == 0)
            System.exit(0);
    }

    //leesPlaatsen
    public void leesPlaatsen (File file){
        ArrayList<String[]> CSV = new ArrayList<>();
        plaatsen = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                CSV.add(input.nextLine().split(";"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < CSV.size(); i ++){
            plaatsen.add(new Plaats(CSV.get(i)[0], CSV.get(i)[1], CSV.get(i)[3], CSV.get(i)[2]));

        }
    }

    //leesAfstanden
    public void leesAfstanden (File file){

        ArrayList<String[]> CSV = new ArrayList<>();
        afstanden = new double[plaatsen.size()][plaatsen.size()];

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                CSV.add(input.nextLine().split(";"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < CSV.size(); i ++){
            for (int j = 0; j < CSV.size(); j++){
                afstanden[i][j] = Double.parseDouble(CSV.get(i)[j]);
            }
        }
    }

    // getAfstand tussen 2 plaatsen
    public double getAfstand (String naamPlaats1, String naamPlaats2){
        int indexPlaats1 = 0;
        int indexPlaats2 = 0;
        for ( int i = 1; i < plaatsen.size()+1; i++){
            if (plaatsen.get(i-1).getNaam().equals(naamPlaats1)) {
                indexPlaats1 = i;
            }
            else if (plaatsen.get(i-1).getNaam().equals(naamPlaats2)){
                indexPlaats2 = i;
            }
        }

        if (indexPlaats1 != 0 && indexPlaats2 != 0){
            return afstanden[indexPlaats1 - 1][indexPlaats2 - 1];
        }
        else return 0;
    }

    //InitialiseerRoute

    public Route initialiseerRoute() {
        ArrayList<Plaats> tussenstops = new ArrayList<>();
        for (Plaats p : plaatsen)
            if (!p.equals(startPlaats) && !p.equals(eindPlaats))
                tussenstops.add(p);
        Collections.shuffle(tussenstops);
        return new Route(startPlaats, eindPlaats, tussenstops);
    }

    //TotaleAfstand

    public double totaleAfstand (Route route){
        double totaleAfstandRoute = 0;
        for (int i = 0; i < route.getAantalPlaatsen() - 1; i++){
            totaleAfstandRoute += getAfstand(route.getPlaats(i).getNaam(),route.getPlaats(i+1).getNaam());
        }
        return totaleAfstandRoute;
    }

    // aantal mogelijke routes

    private int faculteit (int n){
        if (n == 1)
            return 1;
        else
            return n*faculteit(n-1);
    }

    public int aantalRoutes (){
        return faculteit(plaatsen.size()-2);
    }

    // korste route

    public Route bepaalSnelsteRoute (){
        ArrayList<Route> mogelijkeRoutes = new ArrayList<Route>();
        while ( mogelijkeRoutes.size() < aantalRoutes()){
            Route nieuweRoute = initialiseerRoute();
            nieuweRoute.setTotaleAfstand(totaleAfstand(nieuweRoute));
            if (!mogelijkeRoutes.contains(nieuweRoute)){
                mogelijkeRoutes.add(nieuweRoute);
            }
        }
        Collections.sort(mogelijkeRoutes);
        return mogelijkeRoutes.get(0);
    }

    public static void main(String[] args) {
        TSP test = new TSP("plaatsen.csv", "afstanden.txt", "Essentiel Gent Centrum", "Essentiel Knokke");
        System.out.println(test.plaatsen.get(0).getNaam()); // Essentiel Gent Centrum
        System.out.println(test.getAfstand("Essentiel Antwerp Centrum","Essentiel Antwerp Berchem")); // 44.1
        System.out.println(test.faculteit(12)); //479001600
        System.out.println(test.aantalRoutes());
        System.out.println(test.bepaalSnelsteRoute().getTotaleAfstand());
    }
}
