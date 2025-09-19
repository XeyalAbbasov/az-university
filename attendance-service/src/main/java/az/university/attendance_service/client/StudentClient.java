package az.university.attendance_service.client;

import az.university.attendance_service.dto.StudentIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-service",path = "students")
public interface StudentClient {

    @GetMapping("get-id/{id}")
    StudentIdDto getStudentId(@PathVariable Long id);
}
