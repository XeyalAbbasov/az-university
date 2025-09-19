package az.university.student_service.request;

import lombok.Getter;

@Getter
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private String phone;
}
