package valerio.BingeBookBE.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
//    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.NONE)
    private BigInteger id;
    private String name;

    @OneToMany(mappedBy = "roleRef")
    private Set<User> userIds = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }
}
