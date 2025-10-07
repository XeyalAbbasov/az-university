package az.university.teacher_service.controller;

import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.request.CreateComponentRequest;
import az.university.teacher_service.request.UpdateComponentRequest;
import az.university.teacher_service.response.ComponentAddResponse;
import az.university.teacher_service.service.GradeComponentService;
import az.university.teacher_service.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/components")
public class GradeComponentController {


    private GradeComponentService gradeComponentService;

    public GradeComponentController(GradeComponentService gradeComponentService) {
        this.gradeComponentService = gradeComponentService;
    }

    @PostMapping("/{lessonId}")
    public ResponseEntity<ComponentAddResponse> createComponent(@PathVariable Long lessonId, @Valid @RequestBody CreateComponentRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }
        ComponentAddResponse response = gradeComponentService.createComponent(lessonId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentAddResponse> updateComponent(@PathVariable Long id, @Valid @RequestBody UpdateComponentRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }
        ComponentAddResponse response = gradeComponentService.updateComponent(id, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
