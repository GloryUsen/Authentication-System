package com.glory.Authentication.model;

import com.glory.Authentication.enumeration.ProfileStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AppUser  implements UserDetails, Principal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    @Column(unique=true)
    private String email;
    private String password;
    private  String firstName;
    private String lastName;
    private String phoneNumber;
    private Instant dateOfBirthday;
    @OneToOne(fetch =  FetchType.LAZY)
    private  Address address;
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    private boolean accountLock;

    private boolean isEnable;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="appuser_role_junction",
            joinColumns = {@JoinColumn(name="appuser_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }

    @Override
    public String getName() {
        return email;
    }
}
