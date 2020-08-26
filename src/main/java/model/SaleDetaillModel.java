package model;

public class SaleDetaillModel {
    private String itemId;
    private float quantityItem;
    private float price;

    public SaleDetaillModel() {
    }

    public SaleDetaillModel(String itemId, float quantityItem, float price) {
        this.itemId = itemId;
        this.quantityItem = quantityItem;
        this.price = price;
    }

    public float getPriceOfSale() {
        return this.quantityItem * this.price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public float getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(float quantityItem) {
        this.quantityItem = quantityItem;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaleDetaillModel{" +
                "itemId='" + itemId + '\'' +
                ", quantityItem=" + quantityItem +
                ", price=" + price +
                '}';
    }
}
