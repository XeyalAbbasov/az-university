package az.university.teacher_service.client;

import az.university.teacher_service.dto.StudenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "student-service",path = "/students",contextId = "studentClient")
public interface StudentClient {

    @PostMapping("/by-ids")
    List<StudenDto> getStudentByIds(@RequestBody List<Long> ids);

    @GetMapping("/exists/{id}")
    boolean existsById(@PathVariable Long id);

}
