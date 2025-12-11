package az.university.teacher_service.controller;

import az.university.teacher_service.request.CreateStudentGradeRequest;
import az.university.teacher_service.service.StudentGradeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class StudentGradeController {

    private StudentGradeService  studentGradeService;
    public StudentGradeController(StudentGradeService studentGradeService) {
        this.studentGradeService = studentGradeService;
    }


    @PostMapping("/create")
    public ResponseEntity<Void> createGrades(@Valid @RequestBody CreateStudentGradeRequest request){

        studentGradeService.createGrades(request);

        return  ResponseEntity.ok().build();
    }
}
