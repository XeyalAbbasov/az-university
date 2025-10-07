package az.university.teacher_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateGroupRequest {

    @Size(min = 2, message = "Ad ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Ad ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Ad boş buraxıla bilməz!")
    private String name;  // məsələn: "Math-101"

    @Size(min = 2, message = "Qrup adı ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Qrup adı ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Ad boş buraxıla bilməz!")
    private String codeOfSubject;



}
