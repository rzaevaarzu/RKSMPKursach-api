package com.example.mirea.pksmpkursach.api.controllers;


import com.example.mirea.pksmpkursach.api.dto.LoginDto;
import com.example.mirea.pksmpkursach.api.dto.Message;
import com.example.mirea.pksmpkursach.api.dto.PatientDto;
import com.example.mirea.pksmpkursach.api.factories.PatientDtoFactory;
import com.example.mirea.pksmpkursach.store.entities.Patient;
import com.example.mirea.pksmpkursach.store.repositories.PatientRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientDtoFactory patientDtoFactory;

    public static final String GET_ALL_PATIENTS = "/api/patients";
    public static final String GET_PATIENT_BY_ID = "/api/patients/{patient_id}";
    public static final String CHECK_EMAIL = "/api/patients/email/{email}";
    public static final String CREATE_PATIENT = "/api/patients";
    public static final String LOGIN = "/api/patients/login";
    public static final String EDIT_PATIENT = "/api/patients/{patient_id}";
    public static final String DELETE_PATIENT = "/api/patients/{patient_id}";

    @GetMapping(GET_ALL_PATIENTS)
    public List<PatientDto> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patientDtoFactory.makeListPatientDto(patients);
    }

    @GetMapping(GET_PATIENT_BY_ID)
    public PatientDto getPatientById(@PathVariable("patient_id") int patientId) {

        Patient patient = patientRepository.findById(patientId);
        return patientDtoFactory.makePatientDto(patient);
    }

    @GetMapping(CHECK_EMAIL)
    public Message checkEmail(@PathVariable("email") String email) {
        if (patientRepository.findByEmail(email) != null) {
            return new Message("true");
        }
        else return new Message("false");
    }

    @PostMapping(CREATE_PATIENT)
    public PatientDto createPatient(@RequestBody PatientDto patientDto) {

        Patient patient = patientRepository.saveAndFlush(patientDtoFactory.fromPatientDto(patientDto));
        return patientDtoFactory.makePatientDto(patient);
    }


    @PostMapping(LOGIN)
    public ResponseEntity<Object> login(@RequestBody LoginDto body, HttpServletResponse response) {
        Patient patient = patientRepository.findByEmail(body.getEmail());
        if (patient != null) {
            if (body.getPassword() != patient.getPassword()) {
                return ResponseEntity.badRequest().body(new Message("Invalid login or password!"));
            } else {
                return ResponseEntity.ok(patientDtoFactory.makePatientDto(patient));
            }
        }
        return ResponseEntity.badRequest().body(new Message("Invalid login or password!"));
    }


    @PatchMapping(EDIT_PATIENT)
    public PatientDto editPatient(
            @PathVariable("patient_id") int patientId,
            @RequestBody PatientDto patientDto) {

        Patient patient = patientRepository
                .findById(patientId);

        patient.setFio(patientDto.getFio());
        patient.setAge(patientDto.getAge());
        patient.setHeight(patientDto.getHeight());
        patient.setWeight(patientDto.getWeight());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setPassword(patientDto.getPassword());

        patient = patientRepository.saveAndFlush(patient);

        return patientDtoFactory.makePatientDto(patient);
    }

    @DeleteMapping(DELETE_PATIENT)
    public void deletePatient(@PathVariable("patient_id") int patientId) {

        patientRepository.findById(patientId);
        patientRepository.deleteById(patientId);
    }
}
