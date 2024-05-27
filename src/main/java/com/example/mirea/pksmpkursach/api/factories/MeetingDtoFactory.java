package com.example.mirea.pksmpkursach.api.factories;

import com.example.mirea.pksmpkursach.api.dto.MeetingDto;
import com.example.mirea.pksmpkursach.store.entities.Meeting;
import com.example.mirea.pksmpkursach.store.repositories.DoctorRepository;
import com.example.mirea.pksmpkursach.store.repositories.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeetingDtoFactory {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private DoctorDtoFactory doctorDtoFactory;
    private PatientDtoFactory patientDtoFactory;

    public MeetingDtoFactory(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public MeetingDto makeMeetingDto(Meeting entity) {

        return MeetingDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .time(entity.getTime())
                .doctor_id(entity.getDoctorId())
                .patient_id(entity.getPatientId())
                .build();
    }

    public Meeting fromMeetingDto (MeetingDto dto) {
        Meeting meeting = new Meeting();
        meeting.setDate(dto.getDate());
        meeting.setTime(dto.getTime());
        meeting.setDoctorId(dto.getDoctor_id());
//                .orElseThrow(() -> new NotFoundException(String.format("Doctor with \"%s\" doesn't exist.", dto.getDoctor().getId()))));
        meeting.setPatientId(dto.getPatient_id());
//                .orElseThrow(() -> new NotFoundException(String.format("Patient with \"%s\" doesn't exist.", dto.getPatient().getId()))));

        return meeting;
    }

    public List<MeetingDto> makeListMeetingDto(List<Meeting> entities) {
        List<MeetingDto> meetings = new ArrayList<>();
        for (Meeting entity : entities) {
            meetings.add(makeMeetingDto(entity));
        }
        return meetings;
    }
}
