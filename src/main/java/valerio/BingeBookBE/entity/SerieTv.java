package valerio.BingeBookBE.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "serie_tv")
public class SerieTv {
    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private BigInteger id;

    private String title;

    @Column(name = "last_episode_viewed")
    private int lastEpisodeViewed;

    @Column(name = "last_episode_viewed_season")
    private int lastEpisodeViewedSeason;

    @Column(name = "poster_url")
    private String posterUrl;

    @ManyToMany
    @JoinTable(name = "serie_tv_genre",
            joinColumns = @JoinColumn(name = "serie_tv_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "serie_tv_tag",
            joinColumns = @JoinColumn(name = "serie_tv_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_ref")
    private User userRef;

}
