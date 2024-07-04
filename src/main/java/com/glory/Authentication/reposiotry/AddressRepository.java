package com.glory.Authentication.reposiotry;

import com.glory.Authentication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<Address,Long>,JpaSpecificationExecutor<Address> {
}
