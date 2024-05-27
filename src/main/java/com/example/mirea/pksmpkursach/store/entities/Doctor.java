package com.example.mirea.pksmpkursach.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String fio;

    private String info;

    private String image;

    @ManyToOne
    private Job job;
}
