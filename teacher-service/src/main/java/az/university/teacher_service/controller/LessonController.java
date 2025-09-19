package az.university.teacher_service.controller;

import az.university.teacher_service.dto.LessonDto;
import az.university.teacher_service.request.CreateLessonRequest;
import az.university.teacher_service.response.LessonAddResponse;
import az.university.teacher_service.response.LessonSingleResponse;
import az.university.teacher_service.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private LessonService lessonService;

    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

//    @PostMapping//burda mutle teacher ve group id evveleden olmalidir ki lesson yaransin
//    public ResponseEntity<LessonAddResponse> create(@RequestBody CreateLessonRequest request){
//
//        LessonAddResponse response=lessonService.create(request);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<LessonAddResponse> create(@RequestBody CreateLessonRequest request){

        LessonAddResponse response=lessonService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("/list")
//    public ResponseEntity<LessonListResponse> getAllLessons(){
//
//        LessonListResponse response = lessonService.getAllLessons();
//
//        return new  ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PatchMapping("/{lessonId}/add-lesson/{groupId}")
    public ResponseEntity<Void> addLessonToGroup(@PathVariable Long groupId, @PathVariable Long lessonId){
        lessonService.addLessonToGroup(groupId,lessonId);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/list")
    public ResponseEntity<List<LessonDto>> getAllLessons(){

        List<LessonDto> dtos = lessonService.getAllLessons();

        return new  ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonSingleResponse> getLessonById(@PathVariable Long id){

        LessonSingleResponse response = lessonService.getLessonById(id);

        return new  ResponseEntity<>(response, HttpStatus.OK);
    }
}
