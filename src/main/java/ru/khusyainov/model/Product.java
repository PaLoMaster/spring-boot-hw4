package ru.khusyainov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
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

    @ToString.Exclude
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Cart> carts;

    public Product(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
