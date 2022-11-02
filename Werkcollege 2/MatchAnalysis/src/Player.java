import java.io.Serializable;
import java.util.Objects;

public class Player implements INode, Serializable, Comparable<Player> {

    // Instantievariabelen
    private static final long serialVersionUID = 7;
    private String name;
    private int number;

    // Constructor
    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    // Methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return getName().equals(player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return name + " (" +number+")";
    }

    @Override
    public int compareTo(Player o) {
        if (this.number > o.number) {

            // if current object is greater,then return 1
            return 1;
        }
        else if (this.number < o.number) {

            // if current object is greater,then return -1
            return -1;
        }
        else {

            // if current object is equal to o,then return 0
            return 0;
        }
    }
}
