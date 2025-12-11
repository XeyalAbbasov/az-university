package az.university.student_service.client;

import az.university.student_service.request.CreateStudentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "authentication-service",path = "/auth")
public interface AuthenticationClient {


    @GetMapping("/get-user")
    Long getUserIdByUsername(@RequestParam String username,
                             @RequestHeader("X-Internal-Key") String internalKey);


    @PostMapping("/student-registration")
    void sendTeacherToAuth(@RequestParam Long studentId,
                           @RequestBody CreateStudentRequest request,
                           @RequestHeader("X-Internal-Key") String internalKey,
                           @RequestHeader("X-USER-ROLES") String role);

}
