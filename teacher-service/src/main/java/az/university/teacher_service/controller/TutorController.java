package az.university.teacher_service.controller;


import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.request.CreateTutorRequest;
import az.university.teacher_service.request.UpdateTutorRequest;
import az.university.teacher_service.response.TutorAddResponse;
import az.university.teacher_service.service.TutorService;
import az.university.teacher_service.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/tutors")
public class TutorController {

    private TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }


    @PostMapping("/registration")
    public ResponseEntity<TutorAddResponse> create(@Valid @RequestBody final CreateTutorRequest request,
                                                   @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader,
                                                   BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TUTOR")) {
            throw new MyException("Sizin tutor qeydiyyatı etmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }

        TutorAddResponse response = tutorService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PutMapping("/update/{tutorId}")
    public ResponseEntity<String> update(@PathVariable Long tutorId,
                                         @Valid @RequestBody final UpdateTutorRequest request,
                                         @RequestHeader("X-USER-USERNAME") String username,
                                         @RequestHeader (value = "X-USER-ROLES", required = false) String rolesHeader,
                                         BindingResult br) {


        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TUTOR")) {
            throw new MyException("Sizin tutor qeydiyyatı etmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }

        String status = tutorService.update(tutorId,request,username);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }


    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateTutor(@PathVariable Long id,
                                              @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TUTOR")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }

        tutorService.activateTutor(id);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateTutor(@PathVariable Long id,
                                                @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TUTOR")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }

        tutorService.deactivateTutor(id);

        return ResponseEntity.ok().build();

    }

}
