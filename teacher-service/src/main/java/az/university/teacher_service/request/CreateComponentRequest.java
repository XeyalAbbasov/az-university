package az.university.teacher_service.request;

import lombok.Data;

@Data
public class CreateComponentRequest {


    private String name;       // məsələn: Final, Quiz, Davamiyyət
    private Double maxValue;   // məsələn: Final = 50, Quiz = 10
}
