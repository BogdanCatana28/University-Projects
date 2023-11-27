package ro.tuc.ds2020.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.tuc.ds2020.dtos.builders.AuthenticationBuilder;
import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.dtos.validators.SignUpValidator;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(SignUpRequest signUpRequest) throws ValidatorException {
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
    public User updateUser(Integer id, User updatedUser) throws ValidatorException {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            if (!updatedUser.getEmail().equals(existingUser.getEmail()) && userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new ValidatorException("Email is already in use.");
            }

            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            existingUser.setRole(existingUser.getRole());

            return userRepository.save(existingUser);
        } else {
            throw new ValidatorException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            deleteDevicesByUserId(id);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void deleteDevicesByUserId(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        String deviceApiUrl = "http://172.30.1.2:8081/devices/delete_by_user/" + userId;
        restTemplate.delete(deviceApiUrl);
    }
}

