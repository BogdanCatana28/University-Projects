package ro.tuc.ds2020.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Boolean isAdmin;
}
