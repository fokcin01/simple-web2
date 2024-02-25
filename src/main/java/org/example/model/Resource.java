package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Resource extends AbstractEntity {

    private String name;
    private int price;

    @Override
    public String toString() {
        return "Resource{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Resource(Integer id, String name, int price) {
        super(id);
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
