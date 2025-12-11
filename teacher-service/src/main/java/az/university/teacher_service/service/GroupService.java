package az.university.teacher_service.service;

import az.university.teacher_service.client.AuthenticationClient;
import az.university.teacher_service.client.StudentClient;
import az.university.teacher_service.dto.StudenDto;
import az.university.teacher_service.exception.GroupNotFoundException;
import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.model.*;
import az.university.teacher_service.repository.GroupRepository;
import az.university.teacher_service.repository.GroupStudentRepository;
import az.university.teacher_service.repository.TeacherRepository;
import az.university.teacher_service.request.AddStudentToGroupRequest;
import az.university.teacher_service.request.CreateGroupRequest;
import az.university.teacher_service.request.UpdateGroupRequest;
import az.university.teacher_service.response.GroupAddResponse;
import az.university.teacher_service.response.GroupListResponse;
import az.university.teacher_service.response.GroupSingleResponse;
import az.university.teacher_service.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GroupService {

    private final StudentClient studentClient;
    private final TeacherRepository teacherRepository;
    private GroupRepository groupRepository;
    private ModelMapper modelMapper;
    private TeacherService teacherService;
    private GroupStudentRepository groupStudentRepository;
    private AuthenticationClient authenticationClient;

    public GroupService(GroupRepository groupRepository, ModelMapper modelMapper,
                        TeacherService teacherService, GroupStudentRepository groupStudentRepository, StudentClient studentClient,
                        TeacherRepository teacherRepository, AuthenticationClient authenticationClient) {
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
        this.teacherService = teacherService;
        this.groupStudentRepository = groupStudentRepository;
        this.studentClient = studentClient;
        this.teacherRepository = teacherRepository;
        this.authenticationClient = authenticationClient;
    }


    @Value("${internal.api.key}")
    private String internalApiKey;

    public GroupAddResponse create(String username, CreateGroupRequest request) {

        Long tutorUserId = authenticationClient.getUserIdByUsername(username,internalApiKey);

        Group group = new Group();

        modelMapper.map(request, group);
        group.setCreatedAt(LocalDate.now());
        group.setCreatedBy(tutorUserId);
        group.setActive(true);
        groupRepository.save(group);

        GroupAddResponse response = new GroupAddResponse();
        response.setGroupId(group.getId());
        return response;
    }


    public String update(Long id,String tutorUsername, UpdateGroupRequest request) {

        Group group = findGroupById(id);

        Long tutorId = authenticationClient.getUserIdByUsername(tutorUsername,internalApiKey);

        Long groupOwnerId = group.getCreatedBy();

        if (groupOwnerId.equals(tutorId)) {
            group.setName(request.getName());
            group.setCodeOfSubject(request.getCodeOfSubject());
            group.setCreatedAt(group.getCreatedAt());
            group.setCreatedBy(group.getCreatedBy());
            group.setActive(group.isActive());
            groupRepository.save(group);
            return "Group has been updated successfully ";
        } else
            throw new MyException("You must update your own group !", null, Constants.POSSESSION);

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

    public GroupSingleResponse getGroupById(Long groupId) {

        Group group = findGroupById(groupId);

        GroupSingleResponse response = new GroupSingleResponse();

        modelMapper.map(group, response);
        return response;
    }

    protected Group findGroupById(Long id) {

        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Group could not be found by following ! " + id));

    }


}
