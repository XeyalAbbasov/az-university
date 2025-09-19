package az.university.teacher_service.response;

import lombok.Data;

@Data
public class TeacherSingleResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean active;
}
