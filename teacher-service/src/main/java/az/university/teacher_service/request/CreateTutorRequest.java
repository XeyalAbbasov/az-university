package az.university.teacher_service.request;

import lombok.Data;

@Data
public class CreateTutorRequest {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean active;

}
