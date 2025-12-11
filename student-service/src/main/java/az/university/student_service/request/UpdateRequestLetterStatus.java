package az.university.student_service.request;


import az.university.student_service.util.RequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRequestLetterStatus {


    @NotNull(message = "Semestr boş buraxıla bilməz!")
    private RequestStatus status;
}