package az.university.teacher_service.exception;


public class StudentGradeNotFoundException extends RuntimeException{
    public StudentGradeNotFoundException(String message) {
        super(message);
    }
}
