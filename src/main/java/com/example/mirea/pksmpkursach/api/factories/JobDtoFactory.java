package com.example.mirea.pksmpkursach.api.factories;


import com.example.mirea.pksmpkursach.api.dto.JobDto;
import com.example.mirea.pksmpkursach.api.dto.PatientDto;
import com.example.mirea.pksmpkursach.store.entities.Job;
import com.example.mirea.pksmpkursach.store.entities.Patient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobDtoFactory {
    public JobDto makeJobDto(Job entity) {

        return JobDto.builder()
                .id(entity.getId())
                .job_name(entity.getJob_name())
                .build();
    }

    public Job fromJobDto(JobDto dto) {
        Job job = new Job();
        job.setJob_name(dto.getJob_name());

        return job;
    }

    public List<JobDto> makeListJobDto(List<Job> entities) {
        List<JobDto> jobs = new ArrayList<>();
        for (Job entity : entities) {
            jobs.add(makeJobDto(entity));
        }
        return jobs;
    }
}
