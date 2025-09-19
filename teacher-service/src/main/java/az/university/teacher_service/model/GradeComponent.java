package az.university.teacher_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GradeComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // məsələn: Final, Quiz, Davamiyyət
    private Double maxValue;   // məsələn: Final = 50, Quiz = 10

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",nullable = false)
    private Lesson lesson;
}
