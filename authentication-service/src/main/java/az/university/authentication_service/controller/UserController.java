package az.university.authentication_service.controller;

import az.university.authentication_service.dto.UserDto;
import az.university.authentication_service.model.UserInfo;
import az.university.authentication_service.request.AuthRequest;
import az.university.authentication_service.service.JwtService;
import az.university.authentication_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
            return jwtService.generateToken(request.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @GetMapping("/get-user")
    public ResponseEntity<Long> getUserIdByUsername(@RequestParam String username,@RequestHeader(value = "X-Internal-Key",required = false) String internalKey) {


      Long userId=userService.getUserIdByUsername(username, internalKey);

        return ResponseEntity.ok(userId);
    }
}
