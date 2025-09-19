package az.university.student_service.request;

import az.university.student_service.util.RequestStatus;
import lombok.Data;

@Data
public class RequestLetterStatusUpdateRequest {

    private RequestStatus status;
}
