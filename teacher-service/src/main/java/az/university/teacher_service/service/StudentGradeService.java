package az.university.teacher_service.service;

import az.university.teacher_service.client.StudentClient;
import az.university.teacher_service.exception.StudentGradeNotFoundException;
import az.university.teacher_service.model.GradeComponent;
import az.university.teacher_service.model.StudentGrade;
import az.university.teacher_service.repository.StudentGradeRepository;
import az.university.teacher_service.request.CreateStudentGradeRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeService {

    private StudentGradeRepository studentGradeRepository;
    private StudentClient studentClient;
    private GradeComponentService gradeComponentService;

    public StudentGradeService(StudentGradeRepository studentGradeRepository, StudentClient studentClient, GradeComponentService gradeComponentService) {
        this.studentGradeRepository = studentGradeRepository;
        this.studentClient = studentClient;
        this.gradeComponentService = gradeComponentService;
    }


    public void createGrades(CreateStudentGradeRequest request) {

        GradeComponent comp = gradeComponentService.findGradeComponentById(request.getComponentId());

        boolean doesStudentExist = studentClient.existsById(request.getStudentId());
        if (!doesStudentExist) {
            throw new RuntimeException("Student with id " + request.getStudentId() + " does not exist");

        } else if (request.getGrade() > comp.getMaxValue())

            throw new RuntimeException("Grade cannot exceed max value !");

        else {
            StudentGrade grade = new StudentGrade();
            grade.setGrade(request.getGrade());
            grade.setStudentId(request.getStudentId());
            grade.setComponent(comp);

            studentGradeRepository.save(grade);
        }

    }


    protected StudentGrade findStudentGradeById(Long id) {

        return studentGradeRepository.findById(id).orElseThrow(() -> new StudentGradeNotFoundException("Student's grade could not be found by following ! " + id));
    }
}
