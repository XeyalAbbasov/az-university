package az.university.student_service.controller;

import az.university.student_service.request.CreateRequestLetter;
import az.university.student_service.request.RequestLetterStatusUpdateRequest;
import az.university.student_service.response.RequestLetterResponse;
import az.university.student_service.response.StudentAddResponse;
import az.university.student_service.service.RequestLetterService;
import az.university.student_service.util.RequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestLetterController {

    public RequestLetterService requestLetterService;

    public RequestLetterController(RequestLetterService requestLetterService) {
        this.requestLetterService = requestLetterService;
    }


    @PostMapping
    public ResponseEntity<RequestLetterResponse> createRequest(@RequestBody final CreateRequestLetter request){

        RequestLetterResponse letter=requestLetterService.createRequest(request);

        return new ResponseEntity<>(letter, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/approved")
    public ResponseEntity<RequestLetterResponse> updateStatusApproved(@PathVariable Long id, @RequestBody RequestLetterStatusUpdateRequest request) {

        RequestLetterResponse response = requestLetterService.updateStatusApproved(id,request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}/rejected")
    public ResponseEntity<RequestLetterResponse> updateStatusRejected(@PathVariable Long id, @RequestBody RequestLetterStatusUpdateRequest request) {

        RequestLetterResponse response = requestLetterService.updateStatusRejected(id,request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
