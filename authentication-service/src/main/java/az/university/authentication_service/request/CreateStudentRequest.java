package az.university.authentication_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStudentRequest {


    @Size(min = 2, message = "Istifadəçi adı ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Istifadəçi adı ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Istifadəçi adı boş buraxıla bilməz!")
    private String username;

    @Size(min = 2, message = "Şifrə ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Şifrə adı ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Şifrə adı boş buraxıla bilməz!")
    private String password;

    @NotBlank(message = "E-poçt ünvanı boş buraxıla bilməz!")
    private String email;



}
