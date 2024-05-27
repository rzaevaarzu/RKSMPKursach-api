package com.example.mirea.pksmpkursach.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {

    private int id;

    private String fio;

    private String info;

    private String image;

    private JobDto job;
}
