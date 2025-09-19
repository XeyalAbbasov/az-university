package az.university.teacher_service.controller;

import az.university.teacher_service.request.CreateComponentRequest;
import az.university.teacher_service.response.ComponentAddResponse;
import az.university.teacher_service.service.GradeComponentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/components")
public class GradeComponentController {


    private GradeComponentService gradeComponentService;

    public GradeComponentController(GradeComponentService gradeComponentService) {
        this.gradeComponentService = gradeComponentService;
    }


    @PostMapping("/{lessonId}")
    public ResponseEntity<ComponentAddResponse> createComponent(@PathVariable Long lessonId, @RequestBody CreateComponentRequest request){

        ComponentAddResponse response = gradeComponentService.createComponent(lessonId,request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
