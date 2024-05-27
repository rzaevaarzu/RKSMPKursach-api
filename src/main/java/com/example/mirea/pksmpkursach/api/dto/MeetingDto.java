package com.example.mirea.pksmpkursach.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDto {
    private int id;

    private String date;

    private String time;

    private int patient_id;

    private int doctor_id;
}
