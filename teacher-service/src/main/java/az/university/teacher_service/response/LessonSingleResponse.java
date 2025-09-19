package az.university.teacher_service.response;

import az.university.teacher_service.util.Semester;
import lombok.Data;


@Data
public class LessonSingleResponse {
    private Long id;

    private String name;
    private Integer credit;

    private Semester semester;
    private String code;

}
