import java.util.ArrayList;
public class Basket implements Transaction {
    private String clientID;
    private String basketID;
    private ArrayList<BasketItem> basketItems;

    //Constructor
    public Basket(String basketID, String clientID) {
        this.clientID = clientID;
        this.basketID = basketID;
        basketItems = new ArrayList<>();
    }

    //Methodes
    public String getClientID() {
        return clientID;
    }
    public String getBasketID() {
        return basketID;
    }
    @Override
    public ArrayList<Item> getItems() {
        return new ArrayList<>(basketItems);
    }
    @Override
    public boolean containsItem(Item item) {
        return getItems().contains(item);
    }
    @Override
    public boolean containsItemSet(Item[] itemSet) {
        if(itemSet == null || itemSet.length == 0)
            return false;
        for(Item i : itemSet)
            if(!containsItem(i))
                return false;
        return true;
    }
    public static Basket parseBasketLine(String line){
        String[] linesplit = line.split(";");
        Basket b = new Basket(linesplit[0],linesplit[1]);
        for(int i=2;i<linesplit.length;i+=2)
            b.basketItems.add(new BasketItem(linesplit[i],Integer.parseInt(linesplit[i+1])));
        return b;
    }

}