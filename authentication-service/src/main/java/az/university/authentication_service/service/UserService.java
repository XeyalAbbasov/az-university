package az.university.authentication_service.service;

import az.university.authentication_service.dto.UserDto;
import az.university.authentication_service.exception.UserNotFoundException;
import az.university.authentication_service.model.Role;
import az.university.authentication_service.model.UserInfo;
import az.university.authentication_service.repository.RoleRepository;
import az.university.authentication_service.repository.UserRepository;
import az.university.authentication_service.request.CreateStudentRequest;
import az.university.authentication_service.request.CreateTeacherRequest;
import az.university.authentication_service.request.CreateTutorRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private  RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Value("${internal.api.key}")
    private String internalApiKey;


    public void addTeacherToUserInfo(Long teacherId, CreateTeacherRequest request) {

        checkUsernameExists(request.getUsername());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserInfo user = new UserInfo();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        user.setTypeOfUser("teacher");
        user.setUserId(teacherId);
        userRepository.save(user);

    }

    public void addStudentToUserinfo(Long studentId, CreateStudentRequest request) {

        checkUsernameExists(request.getUsername());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserInfo user = new UserInfo();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        user.setTypeOfUser("student");
        user.setUserId(studentId);
        userRepository.save(user);
    }


    public void addTutorToUserinfo(Long tutorId, CreateTutorRequest request) {

//        checkUsernameExists(request.getUsername());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserInfo user = new UserInfo();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        user.setTypeOfUser("tutor");
        user.setUserId(tutorId);
        userRepository.save(user);

        List<Role> roles = roleRepository.findByAcademic(1);
        user.getRoles().addAll(roles);
        userRepository.save(user);

    }

    //security seviyyesinde yoxla gor hasninda tekce olanda ishlemir

    public Long getUserIdByUsername(String username) {

        UserInfo user = findUserByUsername(username);
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        return dto.getUserId();
    }

    public void checkUsernameExists(String username){

        Optional<UserInfo> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                //myexception ile de deyishe bilersen
                throw  new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
            }
    }


    protected UserInfo findUserByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User could not be found by following ! " + username));
    }


}
