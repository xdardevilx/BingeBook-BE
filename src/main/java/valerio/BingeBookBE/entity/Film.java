package valerio.BingeBookBE.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private BigInteger id;

    private String title;

    @Column(name = "poster_url")
    private String posterUrl;

    @OneToMany(mappedBy = "film")
    private List<Tag> tags;

    @OneToMany(mappedBy = "film")
    private List<Genre> genres;
}
