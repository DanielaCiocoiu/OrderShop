package server.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double total;

    private Instant timestamp;

    @Embedded
    private Address address;

    @ElementCollection
    private Set<String> telephones = new HashSet<>();

    //owner-ul
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "join_order_product",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private Set<Product> products = new HashSet<>();

   @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Order() {
    }

    public static class Builder {
        private Order order = new Order();

        public Builder setId(int id) {
            order.id = id;
            return this;
        }

        public Builder setTotal(double total) {
            order.total = total;
            return this;
        }

        public Builder setTimestamp(Instant timestamp) {
            order.timestamp = timestamp;
            return this;
        }

        public Builder setAddress(Address address) {
            order.address = address;
            return this;
        }

        public Builder setTelephones(Set<String> telephones) {
            order.telephones = telephones;
            return this;
        }

        public Builder setProducts(Set<Product> products){
            order.products = products;
            return this;
        }

        public Builder setUser(User user){
            order.user = user;
            return this;
        }


        public Order build() {
            return order;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(Set<String> telephones) {
        this.telephones = telephones;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order: " +
                "id: " + id +
                ", total: " + total +
                ", timestamp: " + timestamp +
                ", address: " + address +
                ", telephones: " + telephones +
                ", products: " + products +
                ", user: " + user;
    }
}
