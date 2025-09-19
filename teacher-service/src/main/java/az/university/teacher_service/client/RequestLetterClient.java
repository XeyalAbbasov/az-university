package az.university.teacher_service.client;

import az.university.teacher_service.request.UpdateRequestLetterStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "student-service",path = "/requests",contextId = "requestLetterClient")
public interface RequestLetterClient {

    @PutMapping("/{id}/approved")
    void approve(@PathVariable Long id, @RequestBody UpdateRequestLetterStatus request);

    @PutMapping("/{id}/rejected")
    void rejected(@PathVariable Long id, @RequestBody UpdateRequestLetterStatus request);
}
