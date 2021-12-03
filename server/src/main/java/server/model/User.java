package server.model;

import lib.dto.Category;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.userName = :userName")
//jpql gandeste in instante, selecteaza toate instantele de tip user, unde userName = username, :username - nameParameter
public class User {

    @EmbeddedId
    private UserId userId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Category category;

/*    @OneToMany(mappedBy = "user")
    private Collection<OrderDTO> serviceOrders = new ArrayList<>();*/

    public User(UserId userId, Category category) {
        this.userId = userId;
        this.category = category;

    }

    public User(){
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
