package model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDetaillModel that = (SaleDetaillModel) o;
        return Float.compare(that.quantityItem, quantityItem) == 0 &&
                Float.compare(that.price, price) == 0 &&
                Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, quantityItem, price);
    }
}
