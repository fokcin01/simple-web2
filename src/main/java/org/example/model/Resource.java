package org.example.model;

public class Resource  {


    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String name;
    private int price;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Resource(Integer id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Resource() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
