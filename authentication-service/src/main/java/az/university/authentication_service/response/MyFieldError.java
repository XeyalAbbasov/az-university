package az.university.authentication_service.response;

import lombok.Data;

@Data
public class MyFieldError {
    private String field;
    private String message;
}
