package az.university.teacher_service.controller;


import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.request.CreateAttendanceRequest;
import az.university.teacher_service.request.CreateTeacherRequest;
import az.university.teacher_service.request.UpdateTeacherRequest;
import az.university.teacher_service.response.TeacherAddResponse;
import az.university.teacher_service.response.TeacherListResponse;
import az.university.teacher_service.response.TeacherSingleResponse;
import az.university.teacher_service.service.TeacherService;
import az.university.teacher_service.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    //update yoxdur
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping("/registration")
    public ResponseEntity<TeacherAddResponse> create(@Valid @RequestBody final CreateTeacherRequest request,
                                                     @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader,
                                                     BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }
        System.out.println("rolesHeader = '" + rolesHeader + "'");

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TEACHER")) {
            throw new MyException("Sizin müəllim qeydiyyat etmək hüququnuz yoxdur! ", br, Constants.POSSESSION);
        }
        TeacherAddResponse response = teacherService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody final UpdateTeacherRequest request,
                                         @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader,
                                         BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }
        System.out.println("rolesHeader = '" + rolesHeader + "'");

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TEACHER")) {
            throw new MyException("Sizin müəllim məlumatlarını yeniləmək hüququnuz yoxdur! ", br, Constants.POSSESSION);
        }
        String status = teacherService.update(id,request);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }


    @PostMapping("/{teacherId}/attendance")
    public ResponseEntity<String> markAttendance(@PathVariable Long teacherId,
                                                 @RequestBody CreateAttendanceRequest request,
                                                 @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader){
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_FOR_TEACHER")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        String status = teacherService.markAttendance(teacherId,request);

        return new  ResponseEntity<>(status, HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<TeacherListResponse> getAllTeachers(@RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TEACHER")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        TeacherListResponse response = teacherService.getAllTeachers();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherSingleResponse> getTeacherById(@PathVariable Long teacherId,
                                                                @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_FOR_TEACHER")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        TeacherSingleResponse response = teacherService.getTeacherById(teacherId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/activate")
    public ResponseEntity<Void> activateTeacher(@PathVariable Long studentId,
                                                @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TEACHER")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        teacherService.activateTeacher(studentId);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/deactivate")
    public ResponseEntity<Void> deactivateTeacher(@PathVariable Long studentId,
                                                  @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TEACHER")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        teacherService.deactivateTeacher(studentId);

        return ResponseEntity.ok().build();

    }

}
