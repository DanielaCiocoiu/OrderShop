package server.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.userId.userName = :userName")
//jpql gandeste in instante, selecteaza toate instantele de tip user, unde userName = username, :username - nameParameter
public class User {

    @EmbeddedId
    private UserId userId;

    private String password;

    @OneToMany(mappedBy = "user")
    private Collection<Order> serviceOrders = new ArrayList<>();

    public User() {
    }


    public UserId getUserId() {
        return userId;
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

    public Collection<Order> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Collection<Order> serviceOrders) {
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
        return "User:  " +
                "userId: " + userId +
                ", password: " + password + '\'' +
                ", serviceOrders: " + serviceOrders;
    }
}
