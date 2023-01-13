package Model;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {

    private String name;
    private Date birthDate;
    private String publicPlace;
    private String CEP;
    private Double number;
    private String city;

    public UserModel(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public UserModel(String name, Date birthDate, String publicPlace, String CEP, Double number, String city) {
        this.name = name;
        this.birthDate = birthDate;
        this.publicPlace = publicPlace;
        this.CEP = CEP;
        this.number = number;
        this.city = city;
    }

    public UserModel(){

    }
}


