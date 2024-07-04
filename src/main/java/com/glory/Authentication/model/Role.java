package com.glory.Authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="role_id")
    private Integer roleId;

    private String authority;

    public Role(){
        super();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
