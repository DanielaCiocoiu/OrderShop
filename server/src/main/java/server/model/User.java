package server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.userId.userName = :userName"),
        @NamedQuery(name = "User.findByCNP", query = "SELECT u FROM User u WHERE u.userId.CNP = :CNP")})
//jpql gandeste in instante, selecteaza toate instantele de tip user, userName = username, :username - nameParameter
public class User {

    //cheie primara compusa implementata printr-un Embeddable
    @EmbeddedId
    private UserId userId;
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Order> serviceOrders = new ArrayList<>();

    public User() {
    }

//Pentru a accesa individual atributele userName si CNP din cod Java trebuie sa trecem prin obiectul userId in felul urmator:
    public String getUserIdUserName() {
        return userId.getUserName();
    }

    public String getUserIdCNP() {
        return userId.getCNP();
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<Order> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User:  " + userId;
    }
}
