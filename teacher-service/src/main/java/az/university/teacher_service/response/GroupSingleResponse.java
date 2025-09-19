package az.university.teacher_service.response;

import az.university.teacher_service.model.Lesson;
import az.university.teacher_service.model.Teacher;
import az.university.teacher_service.model.Tutor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class GroupSingleResponse {


    private Long id;
    private String name;
    private String codeOfSubject;
    private LocalDateTime createdAt;
    private Tutor createdBy;
    private boolean active;

}
