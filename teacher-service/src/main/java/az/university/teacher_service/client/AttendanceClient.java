package az.university.teacher_service.client;

import az.university.teacher_service.request.CreateAttendanceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "attendance-service",path = "attendances")
public interface AttendanceClient {

    @PostMapping
    void markAttendance(final CreateAttendanceRequest request);


}
