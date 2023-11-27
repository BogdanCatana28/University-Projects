package ro.tuc.ds2020.dtos.validators;

import ro.tuc.ds2020.dtos.requests.LoginRequest;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;

public class LoginValidator extends UserValidator {

    public void validate(LoginRequest loginRequest) throws ValidatorException {
        validateEmail(loginRequest.getEmail());
        validatePassword(loginRequest.getPassword());
    }
}
