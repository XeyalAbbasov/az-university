package az.university.teacher_service.repository;

import az.university.teacher_service.model.GroupStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupStudentRepository extends JpaRepository<GroupStudent,Long> {
    List<GroupStudent> findByGroupId(Long groupId);
}
