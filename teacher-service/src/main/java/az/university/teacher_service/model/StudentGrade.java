package az.university.teacher_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double grade;

    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id",nullable = false)
    private GradeComponent component;
}
