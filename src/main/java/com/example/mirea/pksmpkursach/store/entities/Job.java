package com.example.mirea.pksmpkursach.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true)
    private String job_name;

//    @Builder.Default
//    @OneToMany
//    private List<Doctor> doctors = new ArrayList<>();
}
