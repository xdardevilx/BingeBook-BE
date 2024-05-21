package valerio.BingeBookBE.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String profilePicture;

    @ManyToOne
    @JoinColumn(name = "role_ref")
    private Role roleRef;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;

    @OneToMany(mappedBy = "userRef")
    private Set<SerieTv> serieTvIds = new HashSet<>();

    @OneToMany(mappedBy = "userRef")
    private Set<Film> filmIds = new HashSet<>();

    @OneToMany(mappedBy = "userRef")
    private Set<Tag> tagIds = new HashSet<>();

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.roleRef.getName()));
    }
}
