package com.example.mirea.pksmpkursach.store.repositories;

import com.example.mirea.pksmpkursach.store.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
    Meeting findById(@Param("meeting_id") int id);

    List<Meeting> findAllByPatientId(@Param("patient_id") int patientId);

    List<Meeting> findAllByDateAndDoctorId(@Param("date") String date, @Param("doctor_id") int doctorId);
}
