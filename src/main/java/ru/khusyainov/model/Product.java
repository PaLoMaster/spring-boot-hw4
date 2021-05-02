package ru.khusyainov.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_seq")
    @SequenceGenerator(name = "products_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private int cost;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    List<Buyer> buyers;

    public Product() {
    }

    public Product(String title, int cost, List<Buyer> buyers) {
        this.title = title;
        this.cost = cost;
        this.buyers = buyers;
    }

    public Product(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    @Override
    public String toString() {
        return "Product {" + "id=" + id + ", title='" + title + "'" + ", cost=" + cost + "}";
    }
}
