package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.requests.SignUpRequest;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.enums.UserRole;

public class AuthenticationBuilder {

    private AuthenticationBuilder() {

    }

    public static SignUpRequest toDTO(User user) {
        return SignUpRequest.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(SignUpRequest signUpRequest) {
        return User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .role(signUpRequest.getIsAdmin() != null && signUpRequest.getIsAdmin()
                        ? UserRole.ROLE_ADMIN
                        : UserRole.ROLE_USER)
                .build();
    }
}
