package az.university.teacher_service.controller;

import az.university.teacher_service.dto.StudenDto;
import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.request.AddStudentToGroupRequest;
import az.university.teacher_service.request.CreateGroupRequest;
import az.university.teacher_service.request.UpdateGroupRequest;
import az.university.teacher_service.response.GroupAddResponse;
import az.university.teacher_service.response.GroupListResponse;
import az.university.teacher_service.response.GroupSingleResponse;
import az.university.teacher_service.service.GroupService;
import az.university.teacher_service.util.Constants;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final ModelMapper modelMapper;
    private GroupService groupService;

    public GroupController(GroupService groupService, ModelMapper modelMapper) {
        this.groupService = groupService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<GroupAddResponse> create(@RequestHeader("X-USER-USERNAME") String tutorUsername,
                                                   @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader,
                                                   @Valid @RequestBody CreateGroupRequest request,
                                                   BindingResult br) {

        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_ADD_GROUP")) {
            throw new MyException("Sizin qrup yaratmaq hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }

        GroupAddResponse response = groupService.create(tutorUsername, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{groupId}")
    public ResponseEntity<String> update(@PathVariable Long groupId,
                                         @RequestHeader("X-USER-USERNAME") String tutorUsername,
                                         @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader,
                                         @Valid @RequestBody UpdateGroupRequest request, BindingResult br) {

        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_UPDATE_GROUP")) {
            throw new MyException("Sizin qrup yeniləmə hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }
        String status = groupService.update(groupId, tutorUsername, request);
        return new ResponseEntity<>(status, HttpStatus.CREATED);

    }


    @PatchMapping("/{groupId}/assign-teacher/{teacherId}")
    public ResponseEntity<Void> assignTeacherToGroup(
            @PathVariable Long groupId,
            @PathVariable Long teacherId,
            @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_ADDING")) {
            throw new MyException("Sizin qrupa müəllim əlavə etmək hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }

        groupService.assignTeacherToGroup(groupId, teacherId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{groupId}/add-students")
    ResponseEntity<Void> assignStudentToGroup(@PathVariable Long groupId,
                                              @Valid @RequestBody AddStudentToGroupRequest request,
                                              @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader,
                                              BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(Constants.VALIDATION_MESSAGE, br, Constants.VALIDATION_TYPE);
        }

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_ADDING")) {
            throw new MyException("Sizin qrupa tələbə əlavə etmək hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }
        groupService.assignStudentToGroup(groupId, request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{groupId}/students")
    public ResponseEntity<List<StudenDto>> getStudentsByGroupId(@PathVariable Long groupId,
                                                                @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_GET_STUDENTS")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur ! ", null, Constants.POSSESSION);
        }

        List<StudenDto> students = groupService.getStudentsByGroupId(groupId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/list")
    public ResponseEntity<GroupListResponse> getAllGroups(@RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_GET_STUDENTS")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        GroupListResponse entities = groupService.getAllGroups();

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupSingleResponse> getGroupById(@PathVariable Long id,
                                                             @RequestHeader(value = "X-USER-ROLES", required = false) String rolesHeader) {
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_GET_STUDENTS")) {
            throw new MyException("Sizin tələbə siyahısını görmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        GroupSingleResponse response = groupService.getGroupById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
