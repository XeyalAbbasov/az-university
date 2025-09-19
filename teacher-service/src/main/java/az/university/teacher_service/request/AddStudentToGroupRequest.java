package az.university.teacher_service.request;

import lombok.Data;

import java.util.List;

@Data
public class AddStudentToGroupRequest {

    private List<Long> studentIds;
}
