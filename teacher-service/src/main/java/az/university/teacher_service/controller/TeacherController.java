package az.university.teacher_service.controller;


import az.university.teacher_service.client.AttendanceClient;
import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.request.CreateAttendanceRequest;
import az.university.teacher_service.request.CreateTeacherRequest;
import az.university.teacher_service.response.TeacherAddResponse;
import az.university.teacher_service.response.TeacherListResponse;
import az.university.teacher_service.response.TeacherSingleResponse;
import az.university.teacher_service.service.TeacherService;
import az.university.teacher_service.util.Constans;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {


    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping("/registration")
    public ResponseEntity<TeacherAddResponse> create(@Valid @RequestBody final CreateTeacherRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constans.VALIDATION_MESSAGE, br, Constans.VALIDATION_TYPE);
        }

        TeacherAddResponse response = teacherService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @PostMapping("/{teacherId}/attendance")
    public ResponseEntity<String> markAttendance(@PathVariable Long teacherId,@RequestBody CreateAttendanceRequest request){

        String status = teacherService.markAttendance(teacherId,request);

        return new  ResponseEntity<>(status, HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<TeacherListResponse> getAllTeachers() {

        TeacherListResponse response = teacherService.getAllTeachers();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherSingleResponse> getTeacherById(@PathVariable Long teacherId) {

        TeacherSingleResponse response = teacherService.getTeacherById(teacherId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/activate")
    public ResponseEntity<Void> activateTeacher(@PathVariable Long studentId) {

        teacherService.activateTeacher(studentId);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/deactivate")
    public ResponseEntity<Void> deactivateTeacher(@PathVariable Long studentId) {

        teacherService.deactivateTeacher(studentId);

        return ResponseEntity.ok().build();

    }

}
