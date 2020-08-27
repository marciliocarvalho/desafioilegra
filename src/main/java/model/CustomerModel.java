package model;

import java.util.Objects;

public class CustomerModel {
    private String id;
    private String cnpj;
    private String name;
    private String businessArea;

    public CustomerModel() {
    }

    public CustomerModel(String id, String cnpj, String name, String businessArea) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id='" + id + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", businessArea='" + businessArea + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerModel that = (CustomerModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cnpj, that.cnpj) &&
                Objects.equals(name, that.name) &&
                Objects.equals(businessArea, that.businessArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, name, businessArea);
    }
}
