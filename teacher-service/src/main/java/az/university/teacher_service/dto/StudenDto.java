package az.university.teacher_service.dto;

import lombok.Data;

@Data
public class StudenDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private String phone;
    private boolean active;
}
