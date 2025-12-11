package az.university.authentication_service.service;

import az.university.authentication_service.dto.UserDto;
import az.university.authentication_service.exception.UserNotFoundException;
import az.university.authentication_service.model.UserInfo;
import az.university.authentication_service.repository.UserRepository;
import az.university.authentication_service.request.CreateStudentRequest;
import az.university.authentication_service.request.CreateTeacherRequest;
import az.university.authentication_service.request.CreateTutorRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${internal.api.key}")
    private String internalApiKey;


    public void addTeacherToUserInfo(Long teacherId, CreateTeacherRequest request) {

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

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserInfo user = new UserInfo();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        user.setTypeOfUser("tutor");
        user.setUserId(tutorId);
        userRepository.save(user);
    }

    //security seviyyesinde yoxla gor hasninda tekce olanda ishlemir

    public Long getUserIdByUsername(String username, String internalKey) {

        if (internalKey == null || !internalApiKey.equals(internalKey)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid internal key");

        }

        UserInfo user = findUserByUsername(username);
        UserDto dto = new UserDto();
        dto.setUserId(user.getId());
        return dto.getUserId();
    }


    protected UserInfo findUserByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User could not be found by following ! " + username));
    }


}
