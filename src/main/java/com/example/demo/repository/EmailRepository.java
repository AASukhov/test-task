package com.example.demo.repository;

import com.example.demo.entity.Client;
import com.example.demo.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {
    boolean existsByEmail(String email);
    List<Email> findAllByClient(Client client);
}
