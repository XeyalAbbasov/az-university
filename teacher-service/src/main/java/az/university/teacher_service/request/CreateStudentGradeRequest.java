package az.university.teacher_service.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStudentGradeRequest {

    @NotNull(message = "Maksimum dəyər mütləq daxil edilməlidir!")
    @Min(value = 1, message = "Minimum dəyər ən azı 1 olmalıdır!")
    private Double grade;

    private Long studentId;

    private Long componentId;
}
