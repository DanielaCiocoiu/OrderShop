package lib.dto;

import java.io.Serializable;

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
}
