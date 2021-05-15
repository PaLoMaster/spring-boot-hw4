package ru.khusyainov.model;

import javax.persistence.*;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id",
                referencedColumnName = "id")
    private Cart cart;

    public Buyer() {
    }

    public Buyer(int id, String name) {
        this.id = id;
        this.name = name;
        this.cart = new Cart();
    }

    public Buyer(int id, String name, Cart cart) {
        this.id = id;
        this.name = name;
        this.cart = cart;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Buyer{" + "id=" + id + ", name='" + name + "'" + /*", cart=" + cart + */"}";
    }
}