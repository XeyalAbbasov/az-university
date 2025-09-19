package az.university.student_service.service;

import az.university.student_service.dto.LessonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "teacher-service",path = "/lessons")
public interface LessonClient {


    @GetMapping("/list")
    List<LessonDto> getAllLessons();

}
