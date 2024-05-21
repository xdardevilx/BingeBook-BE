package valerio.BingeBookBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    // @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<SerieTv> serieTv = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<Film> film = new HashSet<>();

    public Genre(String name) {
        this.name = name;
    }
}
