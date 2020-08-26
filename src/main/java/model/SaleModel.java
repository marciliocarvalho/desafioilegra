package model;

import java.util.ArrayList;
import java.util.List;

public class SaleModel {
    private String id;
    private String saleId;
    private List<SaleDetais> saleDetais = new ArrayList<>();
    private SalesmanModel salesmanModel;

    public SaleModel() {
    }

    public SaleModel(String id, String saleId, List<SaleDetais> saleDetais, SalesmanModel salesmanModel) {
        this.id = id;
        this.saleId = saleId;
        this.saleDetais = saleDetais;
        this.salesmanModel = salesmanModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public List<SaleDetais> getSaleDetais() {
        return saleDetais;
    }

    public void setSaleDetais(List<SaleDetais> saleDetais) {
        this.saleDetais = saleDetais;
    }

    public SalesmanModel getSalesmanModel() {
        return salesmanModel;
    }

    public void setSalesmanModel(SalesmanModel salesmanModel) {
        this.salesmanModel = salesmanModel;
    }

    @Override
    public String toString() {
        return "SaleModel{" +
                "id='" + id + '\'' +
                ", saleId='" + saleId + '\'' +
                ", saleDetais=" + saleDetais +
                ", salesmanModel=" + salesmanModel +
                '}';
    }

    public class SaleDetais {
        private String idItem;
        private float quantityItem;
        private float price;

        public SaleDetais() {
        }

        public SaleDetais(String idItem, float quantityItem, float price) {
            this.idItem = idItem;
            this.quantityItem = quantityItem;
            this.price = price;
        }

        public String getIdItem() {
            return idItem;
        }

        public void setIdItem(String idItem) {
            this.idItem = idItem;
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
            return "SaleDetais{" +
                    "idItem='" + idItem + '\'' +
                    ", quantityItem=" + quantityItem +
                    ", price=" + price +
                    '}';
        }
    }
}
