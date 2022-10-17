@SuppressWarnings("serial")
public class GraphAdditionException extends Exception{
    public GraphAdditionException(INode n){
        super("De node '"+ n.toString()+"' bestaat al!");
    }
    public GraphAdditionException(IEdge e) {
        super("De edge '" + e.toString() + "' bestaat al!");
    }
}