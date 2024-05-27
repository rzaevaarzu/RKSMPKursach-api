package com.example.mirea.pksmpkursach.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private int id;

    private String fio;

    private String age;

    private String height;

    private String weight;

    private String phone;

    private String email;

    private String password;
}
