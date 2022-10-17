import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface IGraph {
    public ArrayList<INode> getNodes();
    public void addNode(INode node) throws GraphAdditionException;
    public void addEdge(IEdge edge) throws GraphAdditionException;
    public boolean containsNode(INode node);
    public boolean containsEdge(IEdge edge);
    public int calculateInStrenghtOfNode(INode node) throws GraphQueryException;
    public int calculateOutStrenghtOfNode(INode node) throws GraphQueryException;
    public void exportDataFile(File file) throws IOException;
}
