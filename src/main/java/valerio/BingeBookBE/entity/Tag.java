package valerio.BingeBookBE.entity;

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
public class Tag {
    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private BigInteger id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<SerieTv> serieTv = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    private Set<Film> film = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BigInteger userId;
}
