@SuppressWarnings("serial")

public class GraphQueryException extends Exception {
    public GraphQueryException(Object o){
        super("'" + o.toString() + "' bestaat niet!");
    }
}
