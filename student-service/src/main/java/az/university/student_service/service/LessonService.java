package az.university.student_service.service;

import az.university.student_service.dto.LessonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private LessonClient lessonClient;

    public LessonService(LessonClient lessonClient) {
        this.lessonClient = lessonClient;
    }


    public List<LessonDto> getAllLessons() {

        return lessonClient.getAllLessons();
    }
}
