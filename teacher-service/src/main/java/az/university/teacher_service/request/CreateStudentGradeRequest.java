package az.university.teacher_service.request;

import lombok.Data;

@Data
public class CreateStudentGradeRequest {

    private Double grade;

    private Long studentId;

    private Long componentId;
}
