package com.example.mirea.pksmpkursach.store.repositories;

import com.example.mirea.pksmpkursach.store.entities.Doctor;
import com.example.mirea.pksmpkursach.store.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findById(@Param("doctor_id") int id);
    List<Doctor> findAllByJob(@Param("job") Job job);

}
