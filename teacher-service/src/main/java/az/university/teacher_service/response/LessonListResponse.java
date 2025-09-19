package az.university.teacher_service.response;

import lombok.Data;

import java.util.List;

@Data
public class LessonListResponse {
    private List<LessonSingleResponse> singleResponses;
}
