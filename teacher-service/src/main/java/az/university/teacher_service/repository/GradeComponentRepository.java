package az.university.teacher_service.repository;

import az.university.teacher_service.model.GradeComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeComponentRepository extends JpaRepository<GradeComponent,Long> {
}
