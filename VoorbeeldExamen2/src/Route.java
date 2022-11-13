import java.util.ArrayList;
import java.util.Arrays;

public class Route implements Comparable<Route> {

    //InstantieVar
    private Plaats[] route;
    private double totaleAfstand;

    //Constructor

    public Route(Plaats start, Plaats eind, ArrayList<Plaats> tussenstops) {
        route = new Plaats[tussenstops.size()+2];
        route[0] = start;
        route[tussenstops.size()+1] = eind;
        int i = 1;
        for (Plaats stop : tussenstops){ // beter met andere for
            route[i] = stop;
            i++;
        }
    }

    //CompareTo
    @Override
    public int compareTo(Route o) {
        if (this.totaleAfstand > o.totaleAfstand)
            return 1;
        else if (this.totaleAfstand == o.totaleAfstand)
            return 0;
        else
            return -1;
    }

    // Methodes
    public int getAantalPlaatsen (){
        return route.length;
    }

    public double getTotaleAfstand() {
        return totaleAfstand;
    }

    public void setTotaleAfstand(double totaleAfstand) {
        this.totaleAfstand = totaleAfstand;
    }

    public Plaats getPlaats (int index) {
        return route[index];
    }
    public void setPlaats (int index, Plaats plaats){
        route[index] = plaats;
    }

    public ArrayList<Plaats> getTussenStops (){
        ArrayList<Plaats> tussenStops = new ArrayList<>();
        for (int i = 1; i < route.length-1 ; i++){
            tussenStops.add(route[i]);
        }
        return tussenStops;
    }

    @Override
    public String toString() {
        String routeString = "";
        for (int i = 0; i< route.length; i++){
            routeString= routeString + " ==> " + route[i].getNaam();
        }
        return routeString;
    }

    //equals & hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route1 = (Route) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(route, route1.route);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(route);
    }

    public static void main(String[] args) {
        Plaats p1 = new Plaats("Plaats1","a1","b1","1234");
        Plaats p2 = new Plaats("Plaats2","a2","b2","2345");
        Plaats p3 = new Plaats("Plaats3","a3","b3","3456");
        Plaats p4 = new Plaats("Plaats4","a4","b4","4567");
        ArrayList<Plaats> stops = new ArrayList<>();
        stops.add(p2);
        stops.add(p3);
        Route r = new Route(p1,p4,stops);
        System.out.println(r);

    }
}
