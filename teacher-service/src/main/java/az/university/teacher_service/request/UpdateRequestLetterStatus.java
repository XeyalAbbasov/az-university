package az.university.teacher_service.request;

import az.university.teacher_service.util.RequestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
public class UpdateRequestLetterStatus {


    @NotNull(message = "Semestr boş buraxıla bilməz!")
    private RequestStatus status;
}
