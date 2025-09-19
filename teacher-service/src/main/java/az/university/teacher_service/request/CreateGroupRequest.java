package az.university.teacher_service.request;
import lombok.Data;


@Data
public class CreateGroupRequest {

    private String name;  // məsələn: "Math-101"
    private String codeOfSubject;
    private Long teacherId;


}
