package az.university.student_service.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private String phone;
    private boolean active;
}
