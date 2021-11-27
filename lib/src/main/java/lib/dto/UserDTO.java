package lib.dto;

import java.util.Objects;

public class UserDTO {
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

    public UserDTO() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDto = (UserDTO) o;
        return Objects.equals(username, userDto.username) && Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
