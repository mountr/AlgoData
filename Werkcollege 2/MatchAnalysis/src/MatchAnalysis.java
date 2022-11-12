import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MatchAnalysis implements IMatchAnalysis{

    // Inst var
    private String teamName;
    private double ballPossession;
    private Player[] players;
    private IGraph passDistribution;

    // Constr
    public MatchAnalysis(String teamName, double ballPossession, Player[] players, IGraph passDistribution) {
        this.teamName = teamName;
        this.ballPossession = ballPossession;
        this.players = players;
        this.passDistribution = passDistribution;
    }

    // Getters en setters
    public String getTeamName() {
        return teamName;
    }

    public double getBallPossession() {
        return ballPossession;
    }

    public Player[] getPlayers() {
        return players;
    }

    public IGraph getPassDistribution() {
        return passDistribution;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setBallPossession(double ballPossession) {
        this.ballPossession = ballPossession;
    }

    // Methodes
    public static MatchAnalysis readTeamInfo (File file){
        ArrayList<String[]> CSV = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()){
                CSV.add(input.nextLine().split(";"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Het bestand '" + file.getName() + "' bestaat niet!");
            return null;
        }

        String teamName = CSV.get(1)[1];
        double ballPossession = Double.parseDouble(CSV.get(2)[1]);
        int aantalSpelers = Integer.parseInt(CSV.get(3)[1]);

        Player[] players = new Player[aantalSpelers];
        for (int i = 0; i < aantalSpelers; i ++){
            players[i] = new Player(CSV.get(i + 6)[0], Integer.parseInt(CSV.get(i + 6)[1]));
        }

        HashMap<INode, ArrayList<IEdge>> proximityList = new HashMap<>();
        IGraph graph = new Graph(proximityList);

        for (Player player: players){
            try {
                graph.addNode(player);
            } catch (GraphAdditionException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        for (int i = 0; i < aantalSpelers; i++){
            for (int j = 0; j < aantalSpelers; j++){
                int weight = Integer.parseInt(CSV.get(i + aantalSpelers + 8)[j + 1]);
                if (weight > 0) {
                    try {
                        graph.addEdge(new Pass(players[i], players[j], weight));
                    } catch (GraphAdditionException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                }
            }
        }

        return new MatchAnalysis(teamName, ballPossession, players, graph);
    }

    public double calculateGroupIntensity (INode[] nodes){
        double it = 0;

        if (nodes == null || nodes.length == 0)
            return 0;

        for (INode node: nodes){
            try {
                it = it + ((passDistribution.calculateOutStrenghtOfNode(node) +
                        passDistribution.calculateInStrenghtOfNode(node)) / 2);
            } catch (GraphQueryException e) {
                System.out.println(e.getMessage() + "Dus is deze node genegeerd.");;
            }
        }
        it = it / ballPossession;

        return it;
    }

    @Override
    public double calculateTeamIntensity() {
        return calculateGroupIntensity(players);
    }

    @Override
    public String toString() {
        Arrays.sort(players);
        String s = teamName + "\n" + "--------------" + "\n" + "Balbezit: " + ballPossession + "%" + "\n" +
                "Intensiteit: " + calculateTeamIntensity() + "\n" + "Opgestelde Players:";
        for (Player player: players){
            s = s + "\n" + player.toString();
        }
        return s;
    }
}
