package az.university.teacher_service.service;

import az.university.teacher_service.client.StudentClient;
import az.university.teacher_service.dto.StudenDto;
import az.university.teacher_service.exception.GroupNotFoundException;
import az.university.teacher_service.model.*;
import az.university.teacher_service.repository.GroupRepository;
import az.university.teacher_service.repository.GroupStudentRepository;
import az.university.teacher_service.repository.TeacherRepository;
import az.university.teacher_service.request.AddStudentToGroupRequest;
import az.university.teacher_service.request.CreateGroupRequest;
import az.university.teacher_service.response.GroupAddResponse;
import az.university.teacher_service.response.GroupListResponse;
import az.university.teacher_service.response.GroupSingleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    private final StudentClient studentClient;
    private final TeacherRepository teacherRepository;
    private GroupRepository groupRepository;
    private ModelMapper modelMapper;
    private TutorService tutorService;
    private TeacherService teacherService;
    private GroupStudentRepository groupStudentRepository;

    public GroupService(GroupRepository groupRepository, ModelMapper modelMapper, TutorService tutorService,
                        TeacherService teacherService, GroupStudentRepository groupStudentRepository, StudentClient studentClient,
                        TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
        this.tutorService = tutorService;
        this.teacherService = teacherService;
        this.groupStudentRepository = groupStudentRepository;
        this.studentClient = studentClient;
        this.teacherRepository = teacherRepository;
    }


    public GroupAddResponse create(Long tutorId, CreateGroupRequest request) {

        Tutor tutor = tutorService.findTutorById(tutorId);
        Group group = new Group();

        modelMapper.map(request, group);
        group.setCreatedAt(LocalDateTime.now());
        group.setCreatedBy(tutor);
        group.setActive(true);
        groupRepository.save(group);

        GroupAddResponse response = new GroupAddResponse();
        response.setGroupId(group.getId());
        return response;
    }

    public void assignTeacherToGroup(Long groupId, Long teacherId) {
        Group group = findGroupById(groupId);

        Teacher teacher = teacherService.findTeacherById(teacherId);

        group.getTeachers().add(teacher);
        groupRepository.save(group);
    }


    public void assignStudentToGroup(Long groupId, AddStudentToGroupRequest request) {

        Group group = findGroupById(groupId);

        request.getStudentIds().forEach(studentId -> {

            GroupStudent gs = new GroupStudent();
            gs.setGroupId(groupId);
            gs.setStudentId(studentId);
            groupStudentRepository.save(gs);

        });
    }


    public List<StudenDto> getStudentsByGroupId(Long groupId) {
        List<Long> ids = groupStudentRepository.findByGroupId(groupId)
                .stream()
                .map(GroupStudent::getStudentId)
                .toList();
        return studentClient.getStudentByIds(ids);
    }

    public GroupListResponse getAllGroups() {

        List<Group> entities = groupRepository.findAll();


        List<GroupSingleResponse> singleResponseList = entities.stream()
                .map(group -> modelMapper.map(group, GroupSingleResponse.class)).toList();

        GroupListResponse response = new GroupListResponse();
        response.setSingleResponse(singleResponseList);

        return response;


    }

    public GroupSingleResponse getLessonById(Long groupId) {

        Group group = findGroupById(groupId);

        GroupSingleResponse response = new GroupSingleResponse();

        modelMapper.map(group, response);
        return response;
    }

    protected Group findGroupById(Long id) {

        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Group could not be found by following ! " + id));

    }


}
