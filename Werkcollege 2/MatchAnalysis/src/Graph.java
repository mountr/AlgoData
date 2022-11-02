import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph implements IGraph{

    // Instantievariabelen
    private HashMap<INode, ArrayList<IEdge>> proximityLists;

    //Constructors
    public Graph() {
        proximityLists = new HashMap<>();
    }

    public Graph(HashMap<INode, ArrayList<IEdge>> proximityLists) {
        this.proximityLists = proximityLists;
    }

    //Methodes
    @Override
    public ArrayList<INode> getNodes() {
        return new ArrayList<>(proximityLists.keySet());
    }

    public int getNrOfNodes(){
        return proximityLists.keySet().size();
    }

    public int getNrOfEdges(){
        int count = 0;
        for(ArrayList<IEdge> edgeArray : proximityLists.values())
            count += edgeArray.size();
        return count/2; // pas wordt 2 keer geteld
    }

    public INode getNode(String name) throws GraphQueryException {
        for (INode node : proximityLists.keySet())
            if (node.getName().equals(name))
                return node;
        throw new GraphQueryException(name);
    }

    public HashSet<IEdge> getEdgesWithHigherWeight(int weightThreshold){
        HashSet<IEdge> higher = new HashSet<>();
        for(ArrayList<IEdge> edgeList : proximityLists.values())
            for(IEdge edge : edgeList)
                if(edge.getWeight()>weightThreshold)
                    higher.add(edge);
        return higher;
    }

    @Override
    public void addNode(INode node) throws GraphAdditionException {
        if(!proximityLists.containsKey(node))
            proximityLists.put(node, new ArrayList<>());
        else
            throw new GraphAdditionException(node);
    }

    @Override
    public void addEdge(IEdge edge) throws GraphAdditionException {
        if (!containsNode(edge.getStart()) || !containsNode(edge.getEnd()) || containsEdge(edge))
            throw new GraphAdditionException(edge);
        else {
            proximityLists.get(edge.getStart()).add(edge);
            proximityLists.get(edge.getEnd()).add(edge);
        }
    }
    @Override
    public boolean containsNode(INode node) {
        return proximityLists.containsKey(node);
    }

    @Override
    public boolean containsEdge(IEdge edge1) {
        for(ArrayList<IEdge> edgeList : proximityLists.values())
            if(edgeList.contains(edge1))
                return true;

        return false;
    }

    @Override
    public int calculateInStrenghtOfNode(INode node) throws GraphQueryException {
        int weight = 0;
        if (!containsNode(node))
            throw new GraphQueryException(node);
        for (IEdge edge : proximityLists.get(node))
            if (edge.getEnd().equals(node))
                weight += edge.getWeight();
        return weight;
    }

    @Override
    public int calculateOutStrenghtOfNode(INode node) throws GraphQueryException {
        int weight = 0;
        if (!containsNode(node))
            throw new GraphQueryException(node);
        for (IEdge edge : proximityLists.get(node))
            if (edge.getStart().equals(node))
                weight += edge.getWeight();
        return weight;
    }

    @Override
    public void exportDataFile(File file) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        ObjectOutputStream ostream = new ObjectOutputStream(stream);
        ostream.writeObject(proximityLists);
        ostream.close();
        stream.close();
    }

    public static IGraph importDataFile(File file) {
        ObjectInputStream inputStream;
        HashMap<INode,ArrayList<IEdge>> dataFile;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file.getName()));
            dataFile = (HashMap<INode,ArrayList<IEdge>>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
        return new Graph(dataFile);
    }
}
