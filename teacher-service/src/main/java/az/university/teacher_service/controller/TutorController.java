package az.university.teacher_service.controller;


import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.request.CreateTutorRequest;
import az.university.teacher_service.response.TutorAddResponse;
import az.university.teacher_service.service.TutorService;
import az.university.teacher_service.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutors")
public class TutorController {


    private TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }


    @PostMapping("/registration")
//    @PreAuthorize("hasRole('ROLE_ADD_TUTOR')")
    public ResponseEntity<TutorAddResponse> create(@Valid @RequestBody final CreateTutorRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }
        TutorAddResponse response = tutorService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateTutor(@PathVariable Long id) {

        tutorService.activateTutor(id);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateTutor(@PathVariable Long id) {

        tutorService.deactivateTutor(id);

        return ResponseEntity.ok().build();

    }

}
