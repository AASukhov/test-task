package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    private Long phone;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
