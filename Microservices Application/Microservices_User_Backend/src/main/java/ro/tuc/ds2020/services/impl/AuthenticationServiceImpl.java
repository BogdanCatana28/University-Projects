package ro.tuc.ds2020.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.requests.LoginRequest;
import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.dtos.builders.AuthenticationBuilder;
import ro.tuc.ds2020.dtos.validators.LoginValidator;
import ro.tuc.ds2020.dtos.validators.SignUpValidator;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(SignUpRequest signUpRequest) throws ValidatorException {
        SignUpValidator validator = new SignUpValidator();
        try {
            validator.validate(signUpRequest);
        } catch (Exception e) {
            throw new ValidatorException("Validation failed: " + e.getMessage());
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ValidatorException("Email is already in use.");
        }

        User user = AuthenticationBuilder.toEntity(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public String signIn(LoginRequest loginRequest) throws ValidatorException {
        LoginValidator validator = new LoginValidator();
        try {
            validator.validate(loginRequest);
        } catch (Exception e) {
            throw new ValidatorException("Validation failed: " + e.getMessage());
        }

        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return "Authentication successful";
        } else {
            return "Authentication failed";
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
