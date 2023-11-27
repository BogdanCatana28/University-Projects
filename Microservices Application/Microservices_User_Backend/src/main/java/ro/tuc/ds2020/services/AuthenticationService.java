package ro.tuc.ds2020.services;

import ro.tuc.ds2020.dtos.requests.LoginRequest;
import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.User;

public interface AuthenticationService {

    User registerUser(SignUpRequest signUpRequest) throws ValidatorException;

    String signIn(LoginRequest loginRequest) throws ValidatorException;

    User findByEmail(String email);
}