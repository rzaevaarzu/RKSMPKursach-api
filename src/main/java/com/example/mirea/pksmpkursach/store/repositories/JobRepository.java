package com.example.mirea.pksmpkursach.store.repositories;

import com.example.mirea.pksmpkursach.store.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById(@Param("id") int id);
}
