package az.university.teacher_service.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class CreateAttendanceRequest {

    private Long lessonId;                        // dərsin id-si
    private Map<Long, Boolean> studentAttendance; // studentId -> gəldi/gəlmədi
    private LocalDate date;
}
