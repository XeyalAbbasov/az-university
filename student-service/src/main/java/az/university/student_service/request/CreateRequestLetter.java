package az.university.student_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRequestLetter {

    @NotBlank(message = "Başlıq yazılmalıdır!")
    private String title;
    @Size(max = 250,message = "Content maksimum 250 simvol ola bilər")
    private String content;
}
