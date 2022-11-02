import java.util.Objects;

public class BasketItem implements Item{

    // Instantievariabelen
    private String productID;
    private int quantity;

    //Constructor
    public BasketItem(String productID, int quantity) {
        if(quantity <=0) {
            System.out.println("Ongeldige hoeveelheid!");
            System.exit(0);
        }
        else {
            this.productID = productID;
            this.quantity = quantity;
        }
    }

    //Methodes
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getItemName() {
        return productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return productID.equals(that.productID);
    }
    @Override
    public int hashCode() {
        return productID.hashCode();
    }
}
