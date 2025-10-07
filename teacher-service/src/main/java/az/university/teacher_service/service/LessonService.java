package az.university.teacher_service.service;

import az.university.teacher_service.client.AuthenticationClient;
import az.university.teacher_service.dto.LessonDto;
import az.university.teacher_service.exception.LessonNotFoundException;
import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.model.Group;
import az.university.teacher_service.model.Lesson;
import az.university.teacher_service.model.Teacher;
import az.university.teacher_service.repository.LessonRepository;
import az.university.teacher_service.request.CreateLessonRequest;
import az.university.teacher_service.request.UpdateLessonRequest;
import az.university.teacher_service.response.LessonAddResponse;
import az.university.teacher_service.response.LessonSingleResponse;
import az.university.teacher_service.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;
    private GroupService groupService;
    private TeacherService teacherService;
    private AuthenticationClient authenticationClient;

    public LessonService(LessonRepository lessonRepository, ModelMapper modelMapper, GroupService groupService,
                         TeacherService teacherService, AuthenticationClient authenticationClient) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.authenticationClient = authenticationClient;

    }


    //    public LessonAddResponse create(CreateLessonRequest request) {
//
//        Group group =  groupRepository.findById(request.getGroupId()).orElseThrow(()->new GroupNotFoundException("Group not found"));
//        Teacher teacher=teacherRepository.findById(request.getTeacherId()).orElseThrow(()->new TeacherNotFoundException("Teacher not found"));
//
//        Lesson lesson = new Lesson();
//
//        lesson.setName(request.getName());
//        lesson.setCredit(request.getCredit());
//        lesson.setSemester(request.getSemester());
//        lesson.setCode(request.getCode());
//        lesson.setGroup(group);
//        lesson.setTeacher(teacher);
//
//        lessonRepository.save(lesson);
//
//        LessonAddResponse response = new LessonAddResponse();
//
//        response.setLessonId(lesson.getId());
//        return response;
//    }
    public LessonAddResponse create(final CreateLessonRequest request) {

        Teacher teacher = teacherService.findTeacherById(request.getTeacherId());

        Lesson lesson = new Lesson();

        lesson.setName(request.getName());
        lesson.setCredit(request.getCredit());
        lesson.setSemester(request.getSemester());
        lesson.setCode(request.getCode());
        lesson.setTeacher(teacher);

        lessonRepository.save(lesson);

        LessonAddResponse response = new LessonAddResponse();

        response.setLessonId(lesson.getId());
        return response;
    }


    public String update(Long lessonId, String username, final UpdateLessonRequest request) {

        Lesson lesson = findLessonById(lessonId);
        String lessonOwnerUsername = lesson.getTeacher().getUsername();

        if (!username.equals(lessonOwnerUsername)) {
            throw new MyException("You must update your own lesson !", null, Constants.POSSESSION);
        } else {
            modelMapper.map(request, lesson);
            lesson.setGroup(lesson.getGroup());
            lesson.setTeacher(lesson.getTeacher());

            lessonRepository.save(lesson);
            return "Lesson has been updated successfully";
        }
    }


    public void addLessonToGroup(Long groupId, Long lessonId) {

        Group group = groupService.findGroupById(groupId);

        Lesson lesson = findLessonById(lessonId);

        lesson.setGroup(group);

        lessonRepository.save(lesson);

    }

    public List<LessonDto> getAllLessons() {

        List<Lesson> entities = lessonRepository.findAll();

        List<LessonDto> dtoList = entities.stream()
                .map(lesson -> modelMapper.map(lesson, LessonDto.class)).toList();

        return dtoList;
    }

    public LessonSingleResponse getLessonById(Long lessonId) {

        Lesson lesson = findLessonById(lessonId);

        LessonSingleResponse response = new LessonSingleResponse();

        modelMapper.map(lesson, response);
        return response;
    }

    protected Lesson findLessonById(Long id) {

        return lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException("Lesson could not be found by following ! " + id));

    }


}
