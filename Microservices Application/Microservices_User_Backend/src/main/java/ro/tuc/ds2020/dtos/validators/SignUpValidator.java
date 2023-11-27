package ro.tuc.ds2020.dtos.validators;

import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;

public class SignUpValidator extends UserValidator {

    public void validate(SignUpRequest signupRequest) throws ValidatorException {
        validateName(signupRequest.getFirstName());
        validateName(signupRequest.getLastName());
        validateEmail(signupRequest.getEmail());
        validatePassword(signupRequest.getPassword());
    }
}
