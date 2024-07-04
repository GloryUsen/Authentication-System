package com.glory.Authentication.reposiotry;

import com.glory.Authentication.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OtpRepository extends JpaRepository<Otp,Long> , JpaSpecificationExecutor<Otp> {
}
