package az.university.teacher_service.repository;

import az.university.teacher_service.model.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGradeRepository extends JpaRepository<StudentGrade,Long> {
}
