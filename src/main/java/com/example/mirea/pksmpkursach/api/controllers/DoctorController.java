package com.example.mirea.pksmpkursach.api.controllers;

import com.example.mirea.pksmpkursach.api.dto.DoctorDto;
import com.example.mirea.pksmpkursach.api.dto.JobDto;
import com.example.mirea.pksmpkursach.api.factories.DoctorDtoFactory;
import com.example.mirea.pksmpkursach.api.factories.JobDtoFactory;
import com.example.mirea.pksmpkursach.store.entities.Doctor;
import com.example.mirea.pksmpkursach.store.entities.Job;
import com.example.mirea.pksmpkursach.store.repositories.DoctorRepository;
import com.example.mirea.pksmpkursach.store.repositories.JobRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final DoctorDtoFactory doctorDtoFactory;
    private final JobRepository jobRepository;

    public static final String GET_ALL_DOCTORS = "/api/doctors";
    public static final String GET_DOCTOR_BY_ID = "/api/doctors/{doctor_id}";
    public static final String GET_DOCTORS_BY_JOB = "/api/job/doctors";
    public static final String CREATE_DOCTOR = "/api/doctors";
    public static final String EDIT_DOCTOR = "/api/doctors/{doctor_id}";
    public static final String DELETE_DOCTOR = "/api/doctors/{doctor_id}";

    @GetMapping(GET_ALL_DOCTORS)
    public List<DoctorDto> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();

        return doctorDtoFactory.makeListDoctorDto(doctors);
    }

    @GetMapping(GET_DOCTOR_BY_ID)
    public DoctorDto getDoctorById(@PathVariable("doctor_id") int doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId);
//                .orElseThrow(() -> new NotFoundException(String.format("Doctor with \"%s\" doesn't exist.", doctorId)));

        return doctorDtoFactory.makeDoctorDto(doctor);
    }

    @PostMapping(GET_DOCTORS_BY_JOB)
    public List<DoctorDto> getDoctorsByJob(@RequestBody JobDto jobDto) {
        List<Doctor> doctors = doctorRepository.findAllByJob(jobRepository.findById(jobDto.getId()));
//                .orElseThrow(() -> new NotFoundException(String.format("Doctor with \"%s\" doesn't exist.", jobDto.getJob_name())));

        return doctorDtoFactory.makeListDoctorDto(doctors);
    }


    @PostMapping(CREATE_DOCTOR)
    public DoctorDto createDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.saveAndFlush(doctorDtoFactory.fromDoctorDto(doctorDto));

        return doctorDtoFactory.makeDoctorDto(doctor);
    }

    @PatchMapping(EDIT_DOCTOR)
    public DoctorDto editDoctor(
            @PathVariable("doctor_id") int doctorId,
            @RequestBody DoctorDto doctorDto) {

        Doctor doctor = doctorRepository
                .findById(doctorId);
//                .orElseThrow(() -> new NotFoundException(String.format("Doctor with \"%s\" doesn't exist.", doctorId)));

        doctor.setFio(doctorDto.getFio());
        doctor.setInfo(doctorDto.getInfo());
        doctor.setImage(doctorDto.getImage());
        doctor.setJob(jobRepository.findById(doctorDto.getJob().getId())
//                        .orElseThrow(() -> new NotFoundException(String.format("Job with \"%s\" doesn't exist.", doctorDto.getJob().getId())))
                        );

        doctor = doctorRepository.saveAndFlush(doctor);

        return doctorDtoFactory.makeDoctorDto(doctor);
    }

    @DeleteMapping(DELETE_DOCTOR)
    public void deleteDoctor(@PathVariable("doctor_id") int doctorId) {
        doctorRepository
                .findById(doctorId);
//                .orElseThrow(() -> new NotFoundException(String.format("Doctor with \"%s\" doesn't exist.", doctorId)));

        doctorRepository.deleteById(doctorId);
    }
}

