package az.university.teacher_service.request;

import az.university.teacher_service.util.Semester;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateLessonRequest {


    @Size(min = 2, message = "Dərs ən azı 2 hərfdən ibarət olmalıdır!")
    @Size(max = 50, message = "Dərs ən çox 50 hərfdən ibarət ola bilər!")
    @NotBlank(message = "Dərs boş buraxıla bilməz!")
    private String name;

    @NotNull(message = "Qiymət boş ola bilməz!")
    @Positive(message = "Qiymət müsbət olmalıdır!")
    private Integer credit;

    @NotNull(message = "Semestr boş buraxıla bilməz!")
    private Semester semester;

    @NotBlank(message = "Kod boş buraxıla bilməz!")
    private String code;

}
