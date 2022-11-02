import java.util.ArrayList;

public class BasketAnalysis {

    //Instantievariabelen
    private ArrayList<Basket> baskets;

    //Constructor

    public BasketAnalysis(String basketsFileContent){
        if(basketsFileContent == null || basketsFileContent.isEmpty())
            baskets = new ArrayList<>();
        else{
            baskets = new ArrayList<>();
            String[] lines = basketsFileContent.split("\n");
            for(String lijn : lines){
                baskets.add(Basket.parseBasketLine(lijn));
            }
        }
    }

    public static Item[] getItemsFromBaskets(Basket[] baskets){
        ArrayList<Item> items = new ArrayList<>();
        for(Basket b : baskets)
            items.addAll(b.getItems());
        return items.toArray(new Item[0]);
    }
    public Basket[] getClientBaskets(String clientID){
        ArrayList<Basket> bList = new ArrayList<>();
        for(Basket b : baskets)
            if(b.getClientID().equals(clientID))
                bList.add(b);
        return bList.toArray(new Basket[0]);
    }
    public void analyzeClients(String[] clients,int threshold){
        for(String s : clients){
            System.out.println("Analysis Client "+s+" with threshold "+threshold+":\n");
            TransactionAnalysis t = new TransactionAnalysis(getClientBaskets(s),getItemsFromBaskets(getClientBaskets(s)));
            System.out.println(t.aPrioriAlgorithm(threshold));
        }
    }
}
