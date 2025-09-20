package com.sourabh.portfolio.portfolio_backend.repository;

import com.sourabh.portfolio.portfolio_backend.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByEmailAndCode(String email, String code);
    
    // Optional extra: clean up expired OTPs later
    void deleteByExpiryTimeBefore(java.time.LocalDateTime now);
}
