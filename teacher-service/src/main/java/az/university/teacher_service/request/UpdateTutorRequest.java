package az.university.teacher_service.request;
import lombok.Getter;

@Getter
public class UpdateTutorRequest {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private String phone;
}
