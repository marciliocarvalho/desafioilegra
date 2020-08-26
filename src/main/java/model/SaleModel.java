package model;

import java.util.ArrayList;
import java.util.List;

public class SaleModel {
    private String id;
    private String saleId;
    private List<SaleDetaillModel> saleDetais = new ArrayList<>();
    private SalesmanModel salesmanModel;

    public SaleModel() {
    }

    public SaleModel(String id, String saleId, List<SaleDetaillModel> saleDetais, SalesmanModel salesmanModel) {
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

    public SalesmanModel getSalesmanModel() {
        return salesmanModel;
    }

    public void setSalesmanModel(SalesmanModel salesmanModel) {
        this.salesmanModel = salesmanModel;
    }

    public List<SaleDetaillModel> getSaleDetais() {
        return saleDetais;
    }

    public void setSaleDetais(List<SaleDetaillModel> saleDetais) {
        this.saleDetais = saleDetais;
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

}
