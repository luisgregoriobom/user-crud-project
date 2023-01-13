package DTO;

import Model.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String name;
    private Date birthDate;
    private String publicPlace;
    private String CEP;
    private Double number;
    private String city;

    public UserDTO(UserModel user) {
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
        this.publicPlace = user.getPublicPlace();
        this.CEP = user.getCEP();
        this.number = user.getNumber();
        this.city = user.getCity();
    }

    public static UserDTO convertToUserDto(UserModel user) {
        return new UserDTO(user);
    }
}
