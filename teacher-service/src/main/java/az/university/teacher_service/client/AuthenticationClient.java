package az.university.teacher_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authentication-service",path = "/auth")
public interface AuthenticationClient {

    @GetMapping("/{id}")
    String getTeacherUsername(@PathVariable Long id);

    @GetMapping("/get-user")
    Long getUserIdByUsername(@RequestParam String username,
                             @RequestHeader("X-Internal-Key") String internalKey);
}
