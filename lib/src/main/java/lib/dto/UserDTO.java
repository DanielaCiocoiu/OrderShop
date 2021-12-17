package lib.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Serializable {
    //un obiect dedicat transferului intre aplicatii, pastrez entitatile doar la nivel de server
    //este ca o proiectie a entitatii user a ceea ce exista in BD, folosesc un obiect DTO pentru a lucra cu anumite atribute, nu toate
    //ma ajuta sa departajez obiectele persistate de cele pe care le administrez

    private UserIdDTO userId;

    private String password;

    private List<OrderDTO> serviceOrderDtos = new ArrayList<>();

    public UserDTO(UserIdDTO userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserDTO() {
    }


    public UserIdDTO getUserId() {
        return userId;
    }

    public void setUserId(UserIdDTO userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderDTO> getServiceOrderDtos() {
        return serviceOrderDtos;
    }

    public void setServiceOrderDtos(List<OrderDTO> serviceOrderDtos) {
        this.serviceOrderDtos = serviceOrderDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "" + userId;
    }
}
