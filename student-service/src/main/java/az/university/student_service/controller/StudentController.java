package az.university.student_service.controller;


import az.university.student_service.dto.StudentDto;
import az.university.student_service.dto.StudentIdDto;
import az.university.student_service.exception.MyException;
import az.university.student_service.request.CreateStudentRequest;
import az.university.student_service.response.StudentAddResponse;
import az.university.student_service.response.StudentListResponse;
import az.university.student_service.response.StudentSingleResponse;
import az.university.student_service.service.StudentService;
import az.university.student_service.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentAddResponse> create(@RequestBody final CreateStudentRequest request,
                                                     @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_ADD_STUDENT")) {
            throw new MyException("Sizin tələbə qeydiyyatı etmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        StudentAddResponse response = studentService.create(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<StudentListResponse> getAllStudents(@RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader){

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_GET_STUDENTS")) {
            throw new MyException("Sizin tələbə qeydiyyatı etmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        StudentListResponse list = studentService.getAllStudents();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentSingleResponse> getStudentById(@PathVariable Long id){
        StudentSingleResponse response = studentService.getStudentById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get-id/{id}")// bu method sirf id check edib ve hemin id ni ele geri qaytarmaq uchundur
    public ResponseEntity<StudentIdDto> getStudentId(@PathVariable Long id){
        StudentIdDto dto = studentService.getStudentId(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping("/by-ids")
    public ResponseEntity<List<StudentDto>> getStudentByIds (@RequestBody List<Long> studentIds,
                                                             @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader){


        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_GET_STUDENTS")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }
        List<StudentDto> students=studentService.getStudentByIds(studentIds);

        return new ResponseEntity<>(students,HttpStatus.OK);
    }


    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateStudent(@PathVariable Long id,
                                                @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CHANGE_ACTIVATION")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }
        studentService.activateStudent(id);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateStudent(@PathVariable Long id,
                                                  @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CHANGE_ACTIVATION")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }
        studentService.deactivateStudent(id);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/exists/{id}")
    public Boolean doesStudentExist(@PathVariable Long id,
                                    @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_GET_INFORMATION")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }

        return studentService.doesStudentExist(id);
    }

}
