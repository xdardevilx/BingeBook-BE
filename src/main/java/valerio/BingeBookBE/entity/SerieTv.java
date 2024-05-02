package valerio.BingeBookBE.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

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

    @Column(name = "last_episode_viewed_episode")
    private String lastEpisodeViewedSeason;

    @Column(name = "poster_url")
    private String posterUrl;

    @OneToMany(mappedBy = "serieTv")
    private List<Tag> tags;

    @OneToMany(mappedBy = "serieTv")
    private List<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
