package az.university.student_service.controller;

import az.university.student_service.dto.LessonDto;
import az.university.student_service.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student/lessons")
public class LessonController {

    private LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<LessonDto>> getAllLessons() {

        List<LessonDto> lessons=lessonService.getAllLessons();

        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }
}
