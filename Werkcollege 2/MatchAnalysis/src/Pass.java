import java.io.Serializable;

public class Pass implements IEdge, Serializable {

    //Instantievariabelen
    private static final long serialVersionUID = 12;
    private Player sender;
    private Player receiver;
    private int nrOfTimes;

    // Constructor
    public Pass(Player sender, Player receiver, int nrOfTimes) {
        this.sender = sender;
        this.receiver = receiver;
        this.nrOfTimes = nrOfTimes;
    }

    // Methods
    @Override
    public int getWeight() {
        return nrOfTimes;
    }

    @Override
    public INode getStart() {
        return sender;
    }

    @Override
    public INode getEnd() {
        return receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pass pass = (Pass) o;

        if (!sender.equals(pass.sender)) return false;
        return receiver.equals(pass.receiver);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), sender, receiver);
    }

    @Override
    public String toString() {
        return "Pass from " + sender.getName() + " to " + receiver.getName();
    }
}

