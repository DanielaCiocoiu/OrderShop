package server.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//coloanele acestei clase vor constitui cheia compusa
@Embeddable
public class UserId implements Serializable {

    private String userName;
    private String CNP;

    public UserId() {
    }
    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
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
        return Objects.equals(userName, userId.userName) && Objects.equals(CNP, userId.CNP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, CNP);
    }
}
