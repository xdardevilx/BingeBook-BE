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
public class Genre {
    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private BigInteger id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "serie_tv_id")
    private SerieTv serieTv;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public Genre(String name) {
        this.name = name;
    }
}
