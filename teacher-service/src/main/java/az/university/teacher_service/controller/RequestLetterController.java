package az.university.teacher_service.controller;

import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.request.UpdateRequestLetterStatus;
import az.university.teacher_service.service.RequestLetterService;
import az.university.teacher_service.util.Constants;
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


    @PutMapping("/{id}/approved")
    public ResponseEntity<String> updateStatusApproved(@PathVariable Long id,
                                                       @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_REQUEST")) {
            throw new MyException("Sizin tələbəyə cavab bildirmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        requestLetterService.updateStatusApproved(id);
        return ResponseEntity.ok("Approved successfully");
    }

    @PutMapping("/{id}/rejected")
    public ResponseEntity<String> updateStatusRejected(@PathVariable Long id,
                                                       @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_REQUEST")) {
            throw new MyException("Sizin tələbəyə cavab bildirmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        requestLetterService.updateStatusRejected(id);
        return ResponseEntity.ok("Rejected successfully");
    }
}
