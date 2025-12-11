package az.university.authentication_service.controller;

import az.university.authentication_service.dto.UserDto;
import az.university.authentication_service.exception.MyException;
import az.university.authentication_service.model.UserInfo;
import az.university.authentication_service.request.AuthRequest;
import az.university.authentication_service.request.CreateStudentRequest;
import az.university.authentication_service.request.CreateTeacherRequest;
import az.university.authentication_service.request.CreateTutorRequest;
import az.university.authentication_service.service.JwtService;
import az.university.authentication_service.service.UserService;
import az.university.authentication_service.utin.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserService userService;

    public UserController(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String authentication(@RequestBody AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();


            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return jwtService.generateToken(request.getUsername(), roles);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @PostMapping("/teacher-registration")
    public ResponseEntity<Void> teacherRegistration(@RequestParam Long teacherId,
                                                    @RequestBody CreateTeacherRequest request,
                                                    @RequestHeader(value = "X-Internal-Key", required = false) String internalKey,
                                                    @RequestHeader(value = "X-USER-ROLES",required = false) String rolesHeader) {
        System.out.println("rolesHeader = '" + rolesHeader + "'");
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TEACHER")) {
            throw new MyException("Sizin müəllim qeydiyyat etmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }

        userService.addTeacherToUserInfo(teacherId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @PostMapping("student-registration")
    public ResponseEntity<Void> studentRegistration(@RequestParam Long studentId,
                                                    @RequestBody CreateStudentRequest request,
                                                    @RequestHeader(value = "X-Internal-Key",required = false) String internalKey,
                                                    @RequestHeader(value = "X-USER-ROLES",required = false)String rolesHeader) {

        System.out.println("rolesHeader = '" + rolesHeader + "'");

        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_STUDENT")) {
            throw new MyException("Sizin tələbə qeydiyyat etmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }
        userService.addStudentToUserinfo(studentId,request);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/tutor-registration")
    public ResponseEntity<Void> tutorRegistration(@RequestParam Long tutorId,
                                                    @RequestBody CreateTutorRequest request,
                                                    @RequestHeader(value = "X-Internal-Key", required = false) String internalKey,
                                                    @RequestHeader(value = "X-USER-ROLES",required = false) String rolesHeader) {
        System.out.println("rolesHeader = '" + rolesHeader + "'");
        List<String> roles = rolesHeader != null ? Arrays.asList(rolesHeader.split(",")) : List.of();
        if (!roles.contains("ROLE_CONTROL_TUTOR")) {
            throw new MyException("Sizin tutor qeydiyyat etmək hüququnuz yoxdur! ", null, Constants.POSSESSION);
        }

        userService.addTutorToUserinfo(tutorId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @GetMapping("/get-user")
    public ResponseEntity<Long> getUserIdByUsername(@RequestParam String username, @RequestHeader(value = "X-Internal-Key", required = false) String internalKey) {

        Long userId = userService.getUserIdByUsername(username, internalKey);

        return ResponseEntity.ok(userId);
    }
}
