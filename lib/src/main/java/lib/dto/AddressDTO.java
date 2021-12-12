package lib.dto;

import java.io.Serializable;
import java.util.Objects;

public class AddressDTO implements Serializable {

    private final String street;

    private final String number;

    public AddressDTO(String street, String number) {
        this.street = street;
        this.number = number;
    }

    public String getStreets() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO)) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(street, that.street) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number);
    }

    @Override
    public String toString() {
        return "AddressDTO: " +
                "street: " + street +
                ", number: " + number;
    }
}
