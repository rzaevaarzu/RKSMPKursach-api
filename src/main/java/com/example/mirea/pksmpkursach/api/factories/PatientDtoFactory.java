package com.example.mirea.pksmpkursach.api.factories;

import com.example.mirea.pksmpkursach.api.dto.PatientDto;
import com.example.mirea.pksmpkursach.store.entities.Patient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientDtoFactory {

    public PatientDto makePatientDto(Patient entity) {

        return PatientDto.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .age(entity.getAge())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    public Patient fromPatientDto(PatientDto dto) {
        Patient patient = new Patient();
        patient.setFio(dto.getFio());
        patient.setAge(dto.getAge());
        patient.setHeight(dto.getHeight());
        patient.setWeight(dto.getWeight());
        patient.setPhone(dto.getPhone());
        patient.setEmail(dto.getEmail());
        patient.setPassword(dto.getPassword());

        return patient;
    }

    public List<PatientDto> makeListPatientDto(List<Patient> entities) {
        List<PatientDto> patients = new ArrayList<>();
        for (Patient entity : entities) {
            patients.add(makePatientDto(entity));
        }
        return patients;
    }
}
