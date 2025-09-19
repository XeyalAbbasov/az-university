package az.university.student_service.service;

import az.university.student_service.dto.StudentDto;
import az.university.student_service.dto.StudentIdDto;
import az.university.student_service.exception.StudentNotFoundException;
import az.university.student_service.model.Student;
import az.university.student_service.repository.StudentRepository;
import az.university.student_service.request.CreateStudentRequest;
import az.university.student_service.response.StudentAddResponse;
import az.university.student_service.response.StudentListResponse;
import az.university.student_service.response.StudentSingleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {


    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentListResponse getAllStudents() {

        List<Student> list = studentRepository.findAll();
        List<StudentSingleResponse> singleResponses = new ArrayList<>();

        for (Student student : list) {
            StudentSingleResponse s = new StudentSingleResponse();
            modelMapper.map(student, s);
            singleResponses.add(s);

        }
        StudentListResponse listResponse = new StudentListResponse();
        listResponse.setSingleResponse(singleResponses);

        return listResponse;
    }

    public StudentSingleResponse getStudentById(Long id) {

        Student student = findStudentById(id);

        StudentSingleResponse response = new StudentSingleResponse();

        modelMapper.map(student, response);

        return response;

    }

    public StudentIdDto getStudentId(Long id) {

        Student student = findStudentById(id);

        StudentIdDto dto =new  StudentIdDto();
        dto.setStudentId(student.getId());

        return dto;

    }

    public StudentAddResponse create(CreateStudentRequest request) {

        Student student = new Student();
        modelMapper.map(request, student);
        student.setActive(true);
        studentRepository.save(student);

        StudentAddResponse response = new StudentAddResponse();
        response.setStudentId(student.getId());

        return response;
    }

    public void activateStudent(Long studentId) {

        Student student = findStudentById(studentId);
        student.setActive(true);
        studentRepository.save(student);
    }

    public void deactivateStudent(Long studentId) {

        Student student = findStudentById(studentId);
        student.setActive(false);
        studentRepository.save(student);
    }

    public List<StudentDto> getStudentByIds(List<Long> studentIds) {

        List<Student> students = studentRepository.findAllById(studentIds);

        return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();

    }

    public Boolean doesStudentExist(Long studentId) {

        return studentRepository.existsById(studentId);
    }

    protected Student findStudentById(Long id) {

        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student could not be found by following ! " + id));
    }

}
