package az.university.teacher_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTeacherRequest {

    @Size(min = 2, message = "Ad ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Ad ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Ad boş buraxıla bilməz!")
    private String firstName;

    @Size(min = 2, message = "Soyad ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Soyad ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Soyad boş buraxıla bilməz!")
    private String lastName;

    @Size(min = 2, message = "Ata adı ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Ata adı ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Ata adı boş buraxıla bilməz!")
    private String fatherName;

    @Size(min = 2, message = "Istifadəçi adı ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Istifadəçi adı ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Istifadəçi adı boş buraxıla bilməz!")
    private String username;

    @Size(min = 2, message = "Şifrə ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Şifrə ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Şifrə boş buraxıla bilməz!")
    private String password;

    @NotBlank(message = "E-poçt ünvanı boş buraxıla bilməz!")
    private String email;

    @NotBlank(message = "Telefon nömrəsi boş buraxıla bilməz!")
    private String phone;

}
