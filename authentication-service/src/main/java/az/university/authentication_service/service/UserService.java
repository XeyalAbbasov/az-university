package az.university.authentication_service.service;

import az.university.authentication_service.dto.UserDto;
import az.university.authentication_service.exception.UserNotFoundException;
import az.university.authentication_service.model.UserInfo;
import az.university.authentication_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Value("${internal.api.key}")
    private String internalApiKey;;


    public Long getUserIdByUsername(String username,String internalKey) {


        if (internalKey == null || !internalApiKey.equals(internalKey)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Invalid internal key");

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
