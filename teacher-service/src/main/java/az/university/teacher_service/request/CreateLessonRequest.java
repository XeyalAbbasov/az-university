package az.university.teacher_service.request;

import az.university.teacher_service.util.Semester;
import lombok.Data;

@Data
public class CreateLessonRequest {
    private String name;
    private Integer credit;
    private Semester semester;
    private String code;
    private Long teacherId;
}
