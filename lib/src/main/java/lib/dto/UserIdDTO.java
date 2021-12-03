package lib.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserIdDTO implements Serializable {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdDTO userIdDto = (UserIdDTO) o;
        return userName.equals(userIdDto.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

}
