package server.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
//coloana acestei clase va constitui cheia compusa
public class UserId implements Serializable {

    private String userName;

    public UserId() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId userId = (UserId) o;
        return Objects.equals(userName, userId.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userName);
    }
}
