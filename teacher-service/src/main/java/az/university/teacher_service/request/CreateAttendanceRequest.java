package az.university.teacher_service.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class CreateAttendanceRequest {

    private Long lessonId;                        // dərsin id-si
    @NotEmpty(message = "Yoxlama siyahısı boş buraxıla bilməz!")
    @Size(min = 1, message = "Ən azı bir tələbənin iştirakı qeyd olunmalıdır!")
    private Map<Long, Boolean> studentAttendance; // studentId -> gəldi/gəlmədi
    private LocalDate date;
}
