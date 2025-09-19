package az.university.student_service.dto;

import lombok.Data;

@Data
public class LessonDto {

    private String name;
    private Integer credit;
    private String semester;
    private String code;
}
