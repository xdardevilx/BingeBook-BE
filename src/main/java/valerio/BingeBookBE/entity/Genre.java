package valerio.BingeBookBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
//    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private BigInteger id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<SerieTv> serieTv = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public Genre(String name) {
        this.name = name;
    }
}
