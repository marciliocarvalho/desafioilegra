package model;

import java.util.Objects;

public class SalesmanModel {
    private String id;
    private String cpf;
    private String name;
    private float salary;

    public SalesmanModel() {
    }

    public SalesmanModel(String id, String cpf, String name, float salary) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "SalesmanModel{" +
                "id='" + id + '\'' +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesmanModel that = (SalesmanModel) o;
        return Float.compare(that.salary, salary) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, name, salary);
    }
}
