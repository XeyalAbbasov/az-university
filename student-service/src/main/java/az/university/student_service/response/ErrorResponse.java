package az.university.student_service.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ErrorResponse {

    private List<MyFieldError> validations;
    private String message;
    private String type;
    private LocalDate localDate;
}
