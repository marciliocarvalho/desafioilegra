package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaleModel {
    private String id;
    private String saleId;
    private List<SaleDetaillModel> saleDetais = new ArrayList<>();
    private String salesman;

    public SaleModel() {
    }

    public SaleModel(String id, String saleId, List<SaleDetaillModel> saleDetais, String salesmanModel) {
        this.id = id;
        this.saleId = saleId;
        this.saleDetais = saleDetais;
        this.salesman = salesmanModel;
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

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
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
                ", salesmanModel=" + salesman +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleModel saleModel = (SaleModel) o;
        return Objects.equals(id, saleModel.id) &&
                Objects.equals(saleId, saleModel.saleId) &&
                Objects.equals(saleDetais, saleModel.saleDetais) &&
                Objects.equals(salesman, saleModel.salesman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saleId, saleDetais, salesman);
    }
}
