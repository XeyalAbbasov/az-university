package az.university.teacher_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTutorRequest {

    @Size(min = 2,message = "Name must be minimum with 2 letters !")
    @Size(max = 50, message = "Name must be maximum with 50 letters !")
    @NotBlank(message = "Name must be added into the blank! ")
    private String firstName;

    @Size(min = 2,message = "Name must be minimum with 2 letters !")
    @Size(max = 50, message = "Name must be maximum with 50 letters !")
    @NotBlank(message = "Name must be added into the blank! ")
    private String lastName;

    @Size(min = 2,message = "Name must be minimum with 2 letters !")
    @Size(max = 50, message = "Name must be maximum with 50 letters !")
    @NotBlank(message = "Name must be added into the blank! ")
    private String fatherName;

    @NotBlank(message = "Username must be added into the blank! ")
    private String username;

    @NotBlank(message = "Password must be added into the blank! ")
    private String password;

    @NotBlank(message = "Email must be added into the blank! ")
    private String email;

    @NotBlank(message = "Email must be added into the blank! ")
    private String phone;

}
