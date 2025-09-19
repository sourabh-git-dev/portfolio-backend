package com.sourabh.portfolio.portfolio_backend.repository;

import com.sourabh.portfolio.portfolio_backend.model.message;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {}

public interface messagerepository extends JpaRepository<message, Long> {
}
