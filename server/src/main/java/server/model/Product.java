package server.model;

import lib.dto.Category;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@NamedQuery(name = "User.delete", query = "DELETE p FROM Product p WHERE p.id = :id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private double price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Product() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product: " +
                "id: " + id +
                ", productName: '" + productName + '\'' +
                ", price: " + price +
                ", orders: " + orders +
                ", User: " + user;
    }
}

