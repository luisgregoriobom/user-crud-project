package com.app.rest.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
public class UserModel {

    @jakarta.persistence.Id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}


