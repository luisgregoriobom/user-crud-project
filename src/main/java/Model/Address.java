package Model;

import lombok.Data;

import java.util.Date;

@Data
public class Address extends UserModel {

    private String publicPlace;
    private String CEP;
    private Double number;
    private String city;

    public Address(String name, Date birthDate, String publicPlace, String CEP, Double number, String city) {
        super(name, birthDate);
        this.publicPlace = publicPlace;
        this.CEP = CEP;
        this.number = number;
        this.city = city;
    }
}
