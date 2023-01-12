package Model;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {

    private String name;
    private Date birthDate;

    public UserModel(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public UserModel(){

    }
}


