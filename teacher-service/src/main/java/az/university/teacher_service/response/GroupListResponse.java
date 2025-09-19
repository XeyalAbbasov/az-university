package az.university.teacher_service.response;

import lombok.Data;

import java.util.List;

@Data
public class GroupListResponse {

    private List<GroupSingleResponse> singleResponse;
}
