package com.example.mirea.pksmpkursach.api.controllers;

import com.example.mirea.pksmpkursach.api.dto.MeetingDto;
import com.example.mirea.pksmpkursach.api.factories.MeetingDtoFactory;
import com.example.mirea.pksmpkursach.store.entities.Meeting;
import com.example.mirea.pksmpkursach.store.repositories.DoctorRepository;
import com.example.mirea.pksmpkursach.store.repositories.MeetingRepository;
import com.example.mirea.pksmpkursach.store.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingRepository meetingRepository;
    private final MeetingDtoFactory meetingDtoFactory;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public static final String GET_ALL_MEETINGS = "/api/meetings";
    public static final String GET_MEETING_BY_ID = "/api/meetings/{meeting_id}";
    public static final String GET_MEETINGS_BY_PATIENT = "/api/patient/meetings";
    public static final String GET_MEETINGS_BY_DAY = "/api/doctor/day/meetings";
    public static final String CREATE_MEETING = "/api/meetings";
    public static final String EDIT_MEETING = "/api/meetings/{meeting_id}";
    public static final String DELETE_MEETING = "/api/meetings/{meeting_id}";


    @GetMapping(GET_ALL_MEETINGS)
    public List<MeetingDto> getAllMeetings(){
        List<Meeting> meetings = meetingRepository.findAll();
        return meetingDtoFactory.makeListMeetingDto(meetings);
    }

    @GetMapping(GET_MEETING_BY_ID)
    public MeetingDto getMeetingById(@PathVariable("meeting_id") int meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId);
        return meetingDtoFactory.makeMeetingDto(meeting);
    }

    @GetMapping(GET_MEETINGS_BY_PATIENT)
    public List<MeetingDto> getMeetingsByPatient(@RequestParam("patient_id") int patientId) {
        List<Meeting> meetings = meetingRepository.findAllByPatientId(patientId);
//                .orElseThrow(() -> new NotFoundException(String.format("Patient with \"%s\" doesn't exist.", patientId)));

        return meetingDtoFactory.makeListMeetingDto(meetings);
    }

    @GetMapping(GET_MEETINGS_BY_DAY)
    public List<MeetingDto> getMeetingsByDay(@RequestParam("date") String date,
                                             @RequestParam("doctor_id") int doctorId) {
        List<Meeting> meetings = meetingRepository.findAllByDateAndDoctorId(date, doctorId);
//                .orElseThrow(() -> new NotFoundException(String.format("Doctor with \"%s\" doesn't exist.", jobDto.getJob_name())));

        return meetingDtoFactory.makeListMeetingDto(meetings);
    }

    @PostMapping(CREATE_MEETING)
    public MeetingDto createMeeting(@RequestBody MeetingDto meetingDto) {

        Meeting meeting = meetingRepository.saveAndFlush(meetingDtoFactory.fromMeetingDto(meetingDto));
        System.out.println(meetingDto);
////                Meeting.builder()
////                        .date(meetingDto.getDate())
////                        .time(meetingDto.getTime())
////                        .doctor(meetingDto.getDoctor())
////                        .patient(patientDtoFactory.makePatientDto(meetingDto.getPatient()))
////                        .build()
////        );
//
        return meetingDtoFactory.makeMeetingDto(meeting);
    }

    @PatchMapping(EDIT_MEETING)
    public MeetingDto editMeeting(
            @PathVariable("meeting_id") int meetingId,
            @RequestBody MeetingDto meetingDto) {

        Meeting meeting = meetingRepository.findById(meetingId);

        meeting.setDate(meetingDto.getDate());
        meeting.setTime(meetingDto.getTime());
        meeting.setDoctorId(meetingDto.getDoctor_id());
//                .orElseThrow(() -> new NotFoundException(String.format("Doctor with \"%s\" doesn't exist.", meetingDto.getDoctor().getId())))


        meeting.setPatientId(meetingDto.getPatient_id());
//                .orElseThrow(() -> new NotFoundException(String.format("Patient with \"%s\" doesn't exist.", meetingDto.getPatient().getId())))



        meeting = meetingRepository.saveAndFlush(meeting);

        return meetingDtoFactory.makeMeetingDto(meeting);
    }

    @DeleteMapping(DELETE_MEETING)
    public void deleteMeeting(@PathVariable("meeting_id") int meetingId) {

        meetingRepository.findById(meetingId);

        meetingRepository.deleteById(meetingId);
    }

}
