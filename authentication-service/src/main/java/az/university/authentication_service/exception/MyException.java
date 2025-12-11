package az.university.authentication_service.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class MyException extends RuntimeException {

    private String type;
    private BindingResult br;

    public MyException(String message, BindingResult br, String type) {

        super(message);
        this.br = br;
        this.type = type;

    }

}
