package az.university.teacher_service.request;

import az.university.teacher_service.util.RequestStatus;
import lombok.Data;

@Data
public class UpdateRequestLetterStatus {

    private RequestStatus status;
}
