package com.jamsirat.atmapi.model.auth;


import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.statval.enumeration.EGender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "users")
public class User extends AAuditableBase implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1149799443782698228L;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "link_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id",
                                referencedColumnName = "id")
            }, inverseJoinColumns = {
                    @JoinColumn(name = "role_id",referencedColumnName = "id")
    }
    )
    private Set<Role> roles = new HashSet<>();

    @NotNull
    @NotBlank(message = "FirstName is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Column(name = "is_active")
    private Boolean isActive;



    public User(Set<String> roleNames, String firstName, String lastName, String email, String password, Boolean isActive) {
        this.roles = roleNames.stream()
                .map(Role::new)
                .collect(Collectors.toSet());
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}