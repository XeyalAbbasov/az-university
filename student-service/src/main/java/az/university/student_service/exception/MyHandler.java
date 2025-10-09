package az.university.student_service.exception;

import az.university.student_service.response.ErrorResponse;
import az.university.student_service.response.MyFieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class MyHandler {

    @ExceptionHandler
    public ErrorResponse handleMyException(MyException e) {

        ErrorResponse errorResponse = new ErrorResponse();
        BindingResult br = e.getBr();
        if (br != null) {
            List<FieldError> fieldErrors = br.getFieldErrors();
            List<MyFieldError> myList = new ArrayList<>();
            for (FieldError fieldError : fieldErrors) {
                MyFieldError myError = new MyFieldError();

                myError.setField(fieldError.getField());
                myError.setMessage(fieldError.getDefaultMessage());
                myList.add(myError);
            }
            errorResponse.setValidations(myList);
        }
        errorResponse.setLocalDate(LocalDate.now());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setType(e.getType());
        return errorResponse;

    }

    @ExceptionHandler
    public ResponseEntity<?> studentNotFoundException(StudentNotFoundException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseEntity<?> groupNotFoundException(LessonNotFoundException e) {
//
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<?> groupNotFoundException(TeacherNotFoundException e) {
//
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<?> groupNotFoundException(TutorNotFoundException e) {
//
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<?> groupNotFoundException(GradeComponentNotFoundException e) {
//
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
