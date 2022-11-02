import java.util.ArrayList;

public class Basket implements Transaction {

    //Instantievariabelen
    private String clientID;
    private String basketID;
    private ArrayList<BasketItem> basketItems;

    //Constructor


    public Basket(String clientID, String basketID, ArrayList<BasketItem> basketItems) {
        this.clientID = clientID;
        this.basketID = basketID;
        basketItems = new ArrayList<>();
    }

    @Override
    public ArrayList<Item> getItems() {
        return null;
    }

    @Override
    public boolean containsItem(Item item) {
        return false;
    }

    @Override
    public boolean containsItemSet(Item[] itemSet) {
        return false;
    }
}
