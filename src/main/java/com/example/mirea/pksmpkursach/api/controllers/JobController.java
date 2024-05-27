package com.example.mirea.pksmpkursach.api.controllers;

import com.example.mirea.pksmpkursach.api.dto.JobDto;
import com.example.mirea.pksmpkursach.api.factories.JobDtoFactory;
import com.example.mirea.pksmpkursach.store.entities.Job;
import com.example.mirea.pksmpkursach.store.repositories.JobRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class JobController {

    private final JobRepository jobRepository;
    private final JobDtoFactory jobDtoFactory;

    public static final String GET_ALL_JOBS = "/api/jobs";
    public static final String GET_JOB_BY_ID = "/api/jobs/{job_id}";
    public static final String CREATE_JOB = "/api/jobs";
    public static final String EDIT_JOB = "/api/jobs/{job_id}";
    public static final String DELETE_JOB = "/api/jobs/{job_id}";


    @GetMapping(GET_ALL_JOBS)
    public List<JobDto> getAllJobs(){
        List<Job> jobs = jobRepository.findAll();
        return jobDtoFactory.makeListJobDto(jobs);
    }

    @GetMapping(GET_JOB_BY_ID)
    public JobDto getJobById(@PathVariable("job_id") int jobId) {
        Job job = jobRepository.findById(jobId);
//                .orElseThrow(() -> new NotFoundException(String.format("Job with \"%s\" doesn't exist.", jobId)));
        return jobDtoFactory.makeJobDto(job);
    }

    @PostMapping(CREATE_JOB)
    public JobDto createJob(@RequestBody JobDto jobDto) {

        Job job = jobRepository.saveAndFlush(jobDtoFactory.fromJobDto(jobDto));
//                Job.builder()
//                        .job_name(jobDto.getJob_name())
//                        .build()
//        );

        return jobDtoFactory.makeJobDto(job);
    }

    @PatchMapping(EDIT_JOB)
    public JobDto editJob(
            @PathVariable("job_id") int jobId,
            @RequestBody JobDto jobDto) {

        Job job = jobRepository
                .findById(jobId);
//                .orElseThrow(() -> new NotFoundException(String.format("Job with \"%s\" doesn't exist.", jobId)));

        job.setJob_name(jobDto.getJob_name());

        job = jobRepository.saveAndFlush(job);

        return jobDtoFactory.makeJobDto(job);
    }

    @DeleteMapping(DELETE_JOB)
    public void deleteJob(@PathVariable("job_id") int jobId) {

        jobRepository
                .findById(jobId);
//                .orElseThrow(() -> new NotFoundException(String.format("Job with \"%s\" doesn't exist.", jobId)));

        jobRepository.deleteById(jobId);
    }

}
