package ru.khusyainov.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "buyers_products",
            joinColumns = @JoinColumn(
                    name = "buyer_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"))
    private List<Product> products;

    public Buyer() {
    }

    public Buyer(int id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    @Override
    public String toString() {
        return "Buyer{" + "id=" + id + ", name='" + name + "'" + /*", products=" + products + */"}";
    }
}