package lib.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {
    //un obiect dedicat transferului intre aplicatii, pastrez entitatile doar la nivel de server
    //este ca o proiectie a entitatii user a ceea ce exista in BD, folosesc un obiect DTO pentru a lucra cu anumite atribute, nu toate
    //ma ajuta sa departajez obiectele persistate de cele manageuite

    private int id;

    private String username;

    private String password;

    public UserDTO(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    UserDTO() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
