package az.university.student_service.exception;

public class RequestLetterNotFoundException extends RuntimeException{
    public RequestLetterNotFoundException(String message) {
        super(message);
    }
}
