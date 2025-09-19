package az.university.student_service.repository;

import az.university.student_service.model.RequestLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLetterRepository extends JpaRepository<RequestLetter,Long> {
}
