package org.example.model;

public abstract class AbstractEntity {
    private final int id;

    protected AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
