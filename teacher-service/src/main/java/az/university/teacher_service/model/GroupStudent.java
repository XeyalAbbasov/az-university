package az.university.teacher_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "group_students")
@Data
public class GroupStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private Long studentId;
}
