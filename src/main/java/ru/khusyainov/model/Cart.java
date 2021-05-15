package ru.khusyainov.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id",
                referencedColumnName = "id")
    private Buyer buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "carts_products",
            joinColumns = @JoinColumn(
                    name = "cart_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"))
    private List<Product> products;

    public Cart() {

    }

    public Cart(int id, Buyer buyer) {
        this.id = id;
        this.buyer = buyer;
        this.products = new ArrayList<>();
    }

    public Cart(int id, Buyer buyer, List<Product> products) {
        this(id, buyer);
        this.products = products;
    }

    public Cart(Buyer buyer) {
        this(buyer, new ArrayList<>());
    }

    public Cart(Buyer buyer, List<Product> products) {
        this.buyer = buyer;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
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

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public void deleteProduct(Product product) {
        this.products.remove(product);
    }

    public void deleteProducts(List<Product> products) {
        this.products.removeAll(products);
    }

    public void clear() {
        this.products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", buyer=" + buyer + ", products=" + products + "}";
    }
}