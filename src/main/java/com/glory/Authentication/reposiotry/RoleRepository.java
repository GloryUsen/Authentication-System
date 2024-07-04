package com.glory.Authentication.reposiotry;

import com.glory.Authentication.model.AppUser;
import com.glory.Authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

    Optional<Role> findByAuthority(String authority);
}
