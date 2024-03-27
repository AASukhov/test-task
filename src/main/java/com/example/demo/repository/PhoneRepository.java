package com.example.demo.repository;

import com.example.demo.entity.Client;
import com.example.demo.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    boolean existsByPhone(Long phone);
    List<Phone> findAllByClient(Client client);
}
