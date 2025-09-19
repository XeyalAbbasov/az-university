package az.university.teacher_service.exception;

public class TeacherNotFoundException extends RuntimeException {


    public TeacherNotFoundException(String message) {
        super(message);
    }
}
