package lib.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

public class OrderDTO implements Serializable {

    private int id;

    private double total;

    private Instant timestamp;

    private AddressDTO addressDTO;

    private Set<String> telephones = new HashSet<>();

    private Set<Integer> idProducts = new HashSet<>();

    private UserDTO userDTO;

    public OrderDTO() {
    }

    public static class Builder{
        private OrderDTO orderDto = new OrderDTO();

        public Builder setId(int id){
            orderDto.id = id;
            return this;
        }

        public Builder setTotal(double total){
            orderDto.total = total;
            return this;
        }

        public Builder setAddressDTO(AddressDTO addressDTO){
            orderDto.addressDTO = addressDTO;
            return this;
        }

        public Builder setTimestamp(Instant timestamp){
            orderDto.timestamp = timestamp;
            return this;
        }

        public Builder setTelephones(Set<String>  telephones){
            orderDto.telephones = telephones;
            return this;
        }

        public Builder setIdProducts(Set<Integer>  idProducts){
            orderDto.idProducts = idProducts;
            return this;
        }

        public Builder setUser(UserDTO userDTO){
            orderDto.userDTO = userDTO;
            return this;
        }
        public OrderDTO build(){
            return orderDto;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public AddressDTO getAddress() {
        return addressDTO;
    }

    public void setAddress(AddressDTO address) {
        this.addressDTO = address;
    }

    public Set<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(Set<String> telephones) {
        this.telephones = telephones;
    }

    public Set<Integer> getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(Set<Integer> idProducts) {
        this.idProducts = idProducts;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return id == orderDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  "Id Order: " + id + "\n" +
                ",  Total: " + total + "\n" +
                ",  Timestamp: " + timestamp + "\n" +
                ",  " + addressDTO + "\r\n" +
                ",  Telephones: " + telephones;

    }
}