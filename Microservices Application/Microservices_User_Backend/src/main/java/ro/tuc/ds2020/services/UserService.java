package ro.tuc.ds2020.services;

import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Integer id);

    User createUser(SignUpRequest signUpRequest) throws ValidatorException;

    User updateUser(Integer id, User updatedUser) throws ValidatorException;

    void deleteUser(Integer id);
}
