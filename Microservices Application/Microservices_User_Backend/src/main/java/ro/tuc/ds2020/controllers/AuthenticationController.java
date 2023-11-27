package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.requests.LoginRequest;
import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.dtos.responses.LoginResponse;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            User user = authenticationService.registerUser(signUpRequest);

            return ResponseEntity.ok("User registered successfully");
        } catch (ValidatorException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = authenticationService.findByEmail(loginRequest.getEmail());

            if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(new LoginResponse("Authentication successful", user.getRole().toString(), user.getId()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("Bad credentials!", null, null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("Authentication failed", null, null));
        }
    }

}