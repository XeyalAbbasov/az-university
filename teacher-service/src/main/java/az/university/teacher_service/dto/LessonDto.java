package az.university.teacher_service.dto;

import az.university.teacher_service.util.Semester;
import lombok.Data;

@Data
public class LessonDto {
    private Long id;

    private String name;
    private Integer credit;

    private Semester semester;
    private String code;
}
