package az.university.teacher_service.service;

import az.university.teacher_service.client.AttendanceClient;
import az.university.teacher_service.exception.LessonNotFoundException;
import az.university.teacher_service.exception.TeacherNotFoundException;
import az.university.teacher_service.model.Lesson;
import az.university.teacher_service.model.Teacher;
import az.university.teacher_service.repository.LessonRepository;
import az.university.teacher_service.repository.TeacherRepository;
import az.university.teacher_service.request.CreateAttendanceRequest;
import az.university.teacher_service.request.CreateTeacherRequest;
import az.university.teacher_service.response.TeacherAddResponse;
import az.university.teacher_service.response.TeacherListResponse;
import az.university.teacher_service.response.TeacherSingleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {


    private final LessonRepository lessonRepository;
    private TeacherRepository teacherRepository;
    private ModelMapper modelMapper;
    private AttendanceClient attendanceClient;


    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper, AttendanceClient attendanceClient , LessonRepository lessonRepository) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
        this.attendanceClient = attendanceClient;
        this.lessonRepository = lessonRepository;
    }

    public TeacherAddResponse create(final CreateTeacherRequest request) {

        Teacher teacher = new Teacher();
        modelMapper.map(request, teacher);
        String s=request.getEmail();
        teacher.setActive(true);
        teacherRepository.save(teacher);

        TeacherAddResponse response = new TeacherAddResponse();
        response.setTeacherId(teacher.getId());

        return response;
    }

    public TeacherListResponse getAllTeachers() {
        List<Teacher> entities = teacherRepository.findAll();

        List<TeacherSingleResponse> singleResponseList = entities.stream().map(teacher ->
                modelMapper.map(teacher, TeacherSingleResponse.class)).toList();

        TeacherListResponse response = new TeacherListResponse();
        response.setSingleResponses(singleResponseList);

        return response;
    }

    public TeacherSingleResponse getTeacherById(Long id) {

        Teacher teacher = findTeacherById(id);

        TeacherSingleResponse response = new TeacherSingleResponse();

        modelMapper.map(teacher, response);

        return response;
    }

    public void activateTeacher(Long teacherId) {

        Teacher teacher = findTeacherById(teacherId);
        teacher.setActive(true);
        teacherRepository.save(teacher);
    }

    public void deactivateTeacher(Long teacherId) {

        Teacher teacher = findTeacherById(teacherId);
        teacher.setActive(false);
        teacherRepository.save(teacher);
    }


    public String markAttendance(Long teacherId, CreateAttendanceRequest request) {

        Teacher teacher = findTeacherById(teacherId);

        Lesson lesson= lessonRepository.findById(request.getLessonId()).orElseThrow(()-> new LessonNotFoundException("Lesson not found"));
        attendanceClient.markAttendance(request);
        return "mark attendance successfully";

    }

    protected Teacher findTeacherById(Long id) {

        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException("Teacher could not be found by following ! " + id));
    }


}
