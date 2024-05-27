package com.example.mirea.pksmpkursach.api.factories;

import com.example.mirea.pksmpkursach.api.dto.DoctorDto;
import com.example.mirea.pksmpkursach.api.dto.PatientDto;
import com.example.mirea.pksmpkursach.store.entities.Doctor;
import com.example.mirea.pksmpkursach.store.entities.Job;
import com.example.mirea.pksmpkursach.store.entities.Patient;
import com.example.mirea.pksmpkursach.store.repositories.JobRepository;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;


@Component
public class DoctorDtoFactory {

    private final JobDtoFactory jobDtoFactory;
    private final JobRepository jobRepository;

    public DoctorDtoFactory(JobDtoFactory jobDtoFactory, JobRepository jobRepository) {
        this.jobDtoFactory = jobDtoFactory;
        this.jobRepository = jobRepository;
    }

    public DoctorDto makeDoctorDto(Doctor entity) {

        return DoctorDto.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .info(entity.getInfo())
                .image(entity.getImage())
                .job(jobDtoFactory.makeJobDto(entity.getJob())
                        )
                .build();
    }

    public Doctor fromDoctorDto (DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setFio(dto.getFio());
        doctor.setInfo(dto.getInfo());
        doctor.setImage(dto.getImage());
        doctor.setJob(jobRepository.findById(dto.getJob().getId()));
//                .orElseThrow(() -> new NotFoundException(String.format("Job with \"%s\" doesn't exist.", dto.getJob().getId()))));

        return doctor;
    }

    public List<DoctorDto> makeListDoctorDto(List<Doctor> entities) {
        List<DoctorDto> doctors = new ArrayList<>();
        for (Doctor entity : entities) {
            doctors.add(makeDoctorDto(entity));
        }
        return doctors;
    }
}
