package az.university.teacher_service.response;

import lombok.Data;

import java.util.List;

@Data
public class TeacherListResponse {

    private List<TeacherSingleResponse> singleResponses;
}
