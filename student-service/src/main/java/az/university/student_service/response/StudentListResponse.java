package az.university.student_service.response;

import lombok.Data;

import java.util.List;

@Data
public class StudentListResponse {

    private List<StudentSingleResponse> singleResponse;
}
