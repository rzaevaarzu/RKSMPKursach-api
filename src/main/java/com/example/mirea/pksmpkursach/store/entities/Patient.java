package com.example.mirea.pksmpkursach.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String fio;

    private String age;

    private String height;

    private String weight;

    private String phone;

    @Column(unique = true)
    private String email;

    private String password;



}
