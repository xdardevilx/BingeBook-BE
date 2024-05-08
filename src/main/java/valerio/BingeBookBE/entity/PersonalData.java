package valerio.BingeBookBE.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private BigInteger id;

    private String name;
    private String surname;

    @OneToOne(mappedBy = "personalDataId")
    private User user;

    public PersonalData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
