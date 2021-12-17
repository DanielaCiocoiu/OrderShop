package lib.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserIdDTO implements Serializable {

    private String userName;
    private String CNP;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserIdDTO)) return false;
        UserIdDTO userIdDTO = (UserIdDTO) o;
        return userName.equals(userIdDTO.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return userName;
    }
}
