package com.example.mirea.pksmpkursach.store.repositories;

import com.example.mirea.pksmpkursach.store.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findById(@Param("patient_id") int id);

    Patient findByEmail(@Param("email") String email);
}
