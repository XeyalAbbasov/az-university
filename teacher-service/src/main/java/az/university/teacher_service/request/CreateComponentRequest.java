package az.university.teacher_service.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateComponentRequest {


    @NotBlank(message = "Qiymətləndirmə adı boş buraxıla bilməz!")
    private String name;       // məsələn: Final, Quiz, Davamiyyət

    @NotNull(message = "Maksimum dəyər mütləq daxil edilməlidir!")
    @Min(value = 1, message = "Minimum dəyər ən azı 1 olmalıdır!")
    @Max(value = 50, message = "Maksimum dəyər 50-dən çox ola bilməz!")
    @Positive(message = "Maksimum dəyər müsbət olmalıdır!")
    private Double maxValue;   // məsələn: Final = 50, Quiz = 10
}
