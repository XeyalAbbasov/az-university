package az.university.attendance_service.service;

import az.university.attendance_service.client.StudentClient;
import az.university.attendance_service.dto.StudentIdDto;
import az.university.attendance_service.model.Attendance;
import az.university.attendance_service.repository.AttendanceRepository;
import az.university.attendance_service.request.CreateAttendanceRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AttendanceService {


    private AttendanceRepository attendanceRepository;
    private StudentClient studentClient;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentClient studentClient) {
        this.attendanceRepository = attendanceRepository;
        this.studentClient = studentClient;
    }


    public void markAttendance(CreateAttendanceRequest request) {

        for (Map.Entry<Long, Boolean> entry : request.getStudentAttendance().entrySet()) {

            StudentIdDto student = studentClient.getStudentId(entry.getKey());

            Attendance attendance = new Attendance();

            attendance.setLessonId(request.getLessonId());
            attendance.setStudentId(student.getStudentId());
            attendance.setPresent(entry.getValue());
            attendance.setDate(request.getDate());

            attendanceRepository.save(attendance);

        }
    }
}
