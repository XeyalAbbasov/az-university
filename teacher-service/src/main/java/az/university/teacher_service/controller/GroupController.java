package az.university.teacher_service.controller;

import az.university.teacher_service.dto.StudenDto;
import az.university.teacher_service.request.AddStudentToGroupRequest;
import az.university.teacher_service.request.CreateGroupRequest;
import az.university.teacher_service.response.GroupAddResponse;
import az.university.teacher_service.response.GroupListResponse;
import az.university.teacher_service.response.GroupSingleResponse;
import az.university.teacher_service.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    public ResponseEntity<GroupAddResponse> create(@PathVariable Long id,@RequestBody CreateGroupRequest request){

        GroupAddResponse response=groupService.create(id,request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PatchMapping("/{groupId}/assign-teacher/{teacherId}")
    public ResponseEntity<Void> assignTeacherToGroup(
            @PathVariable Long groupId,
            @PathVariable Long teacherId) {

        groupService.assignTeacherToGroup(groupId, teacherId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{groupId}/add-students")
    ResponseEntity<Void> assignStudentToGroup(@PathVariable Long groupId,
                                             @RequestBody AddStudentToGroupRequest request){
        groupService.assignStudentToGroup(groupId,request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{groupId}/students")
    public ResponseEntity<List<StudenDto>> getStudentsByGroupId(@PathVariable Long groupId){
        List<StudenDto> students=groupService.getStudentsByGroupId(groupId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/list")
    public ResponseEntity <GroupListResponse> getAllGroups(){

        GroupListResponse entities = groupService.getAllGroups();

        return new ResponseEntity<>(entities,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupSingleResponse> getLessonById(@PathVariable Long id){

        GroupSingleResponse response = groupService.getLessonById(id);

        return new  ResponseEntity<>(response, HttpStatus.OK);
    }
}
