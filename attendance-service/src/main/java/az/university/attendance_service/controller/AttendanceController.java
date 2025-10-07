package az.university.attendance_service.controller;

import az.university.attendance_service.request.CreateAttendanceRequest;
import az.university.attendance_service.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {


    private AttendanceService attendanceService;


    public AttendanceController(AttendanceService attendanceService) {

        this.attendanceService = attendanceService;
    }


    @PostMapping
    public ResponseEntity<Void> createAttendance(@RequestBody final CreateAttendanceRequest request){

         attendanceService.markAttendance(request);

        return ResponseEntity.ok().build();
    }

}
