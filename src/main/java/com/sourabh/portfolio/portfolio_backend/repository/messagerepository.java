package com.sourabh.portfolio.portfolio_backend.repository;

import com.sourabh.portfolio.portfolio_backend.model.message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface messagerepository extends JpaRepository<message, Long> {
}
