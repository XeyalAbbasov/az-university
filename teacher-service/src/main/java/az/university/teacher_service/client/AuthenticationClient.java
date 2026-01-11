package az.university.teacher_service.client;

import az.university.teacher_service.request.CreateTeacherRequest;
import az.university.teacher_service.request.CreateTutorRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "authentication-service",path = "/auth")
public interface AuthenticationClient {


    @GetMapping("/get-user")
    Long getUserIdByUsername(@RequestParam String username,
                             @RequestHeader("X-Internal-Key") String internalKey);


    @PostMapping("/teacher-registration")
    void sendTeacherToAuth(@RequestParam Long teacherId,
                           @RequestBody CreateTeacherRequest request,
                           @RequestHeader("X-Internal-Key") String internalKey,
                           @RequestHeader("X-USER-ROLES") String role);

    @PostMapping("/tutor-registration")
    void sendTutorToAuth(@RequestParam Long tutorId,
                         @RequestBody CreateTutorRequest request,
                         @RequestHeader("X-Internal-Key") String internalApiKey,
                         @RequestHeader("X-USER-ROLES") String role);

//student de userServise elave olunmalidir amma olunmayib novbeti stepde et bunu.


    @GetMapping("/check-user")
    void checkUsernameExists (@RequestParam String username,
                              @RequestHeader("X-Internal-Key") String internalKey);
    
}
