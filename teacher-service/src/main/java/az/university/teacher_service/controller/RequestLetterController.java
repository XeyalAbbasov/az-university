package az.university.teacher_service.controller;

import az.university.teacher_service.request.UpdateRequestLetterStatus;
import az.university.teacher_service.service.RequestLetterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestLetterController {

    public RequestLetterService requestLetterService;

    public RequestLetterController(RequestLetterService requestLetterService) {
        this.requestLetterService = requestLetterService;
    }


    @PutMapping("/{id}/approved")
    public ResponseEntity<String> updateStatusApproved(@PathVariable Long id, @RequestBody UpdateRequestLetterStatus request) {
        requestLetterService.updateStatusApproved(id, request);
        return ResponseEntity.ok("Approved successfully");
    }

    @PutMapping("/{id}/rejected")
    public ResponseEntity<String> updateStatusRejected(@PathVariable Long id, @RequestBody UpdateRequestLetterStatus request) {
        requestLetterService.updateStatusRejected(id,request);
        return ResponseEntity.ok("Rejected successfully");
    }
}
