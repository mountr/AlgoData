import java.util.ArrayList;

public interface Transaction {

    public ArrayList<Item> getItems();

    public boolean containsItem(Item item);

    public boolean containsItemSet(Item[] itemSet);
}