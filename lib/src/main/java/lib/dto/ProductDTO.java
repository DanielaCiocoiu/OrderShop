package lib.dto;

import java.io.Serializable;
import java.util.Objects;

public class ProductDTO implements Serializable {

    private int id;

    private String productName;

    private double price;

    private Category category;

    private UserDTO userDTO;

    public ProductDTO(String productName, double price, Category category, UserDTO userDTO) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.userDTO = userDTO;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductDto: " +
                "id: " + id +
                ", product name: " + productName +
                ", price: " + price +
                ", category: " + category +
                ", userDTO: " + userDTO;
    }
}
