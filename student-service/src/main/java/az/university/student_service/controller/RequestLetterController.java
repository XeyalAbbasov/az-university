package az.university.student_service.controller;

import az.university.student_service.exception.MyException;
import az.university.student_service.request.CreateRequestLetter;
import az.university.student_service.response.RequestLetterResponse;
import az.university.student_service.service.RequestLetterService;
import az.university.student_service.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestLetterController {

    public RequestLetterService requestLetterService;

    public RequestLetterController(RequestLetterService requestLetterService) {
        this.requestLetterService = requestLetterService;
    }


    @PostMapping
    public ResponseEntity<RequestLetterResponse> createRequest(@Valid @RequestBody final CreateRequestLetter request,
                                                               @RequestHeader("X-USER-USERNAME") String username ){

        RequestLetterResponse letter=requestLetterService.createRequest(username,request);

        return new ResponseEntity<>(letter, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/approved")
    public ResponseEntity<Void> updateStatusApproved(@PathVariable Long id,
                                                     @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {


        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_REQUEST")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
         requestLetterService.updateStatusApproved(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/{id}/rejected")
    public ResponseEntity<RequestLetterResponse> updateStatusRejected(@PathVariable Long id,
                                                                      @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_REQUEST")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
         requestLetterService.updateStatusRejected(id);

        return new ResponseEntity<>( HttpStatus.OK);

    }

}
