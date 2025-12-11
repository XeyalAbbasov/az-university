package az.university.teacher_service.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class AddStudentToGroupRequest {

    @NotEmpty(message = "Tələbə siyahısında ən azı bir tələbə olmalıdır!")
    private List<Long> studentIds;
}
