package ro.tuc.ds2020.dtos.validators;

import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UserValidator {

    public void validateName(String name) throws ValidatorException {
        if (name == null || name.isEmpty()) {
            throw new ValidatorException("Name cannot be empty");
        }

        if (!name.matches("^[A-Za-z -]+$")) {
            throw new ValidatorException("Name can't contain digits!");
        }
    }

    public void validateEmail(String email) throws ValidatorException {
        if (email == null || email.isEmpty()) {
            throw new ValidatorException("Email cannot be empty");
        }
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Za-z0-9._%+-]+@[a-z.-]+\\.(com|ro)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if (!matcher.find()) {
            throw new ValidatorException("Invalid email format");
        }
    }

    public void validatePassword(String password) throws ValidatorException {
        if (password == null || password.isEmpty()) {
            throw new ValidatorException("Password cannot be empty");
        }

        if (password.length() < 8) {
            throw new ValidatorException("Password must have at least 8 characters");
        }
    }
}
