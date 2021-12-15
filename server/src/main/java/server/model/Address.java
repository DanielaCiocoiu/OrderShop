package server.model;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String street;

    private String number;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(number, address.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number);
    }

    @Override
    public String toString() {
        return "Address: " +
                "street: " + street +
                ", number: " + number;
    }
}
