package com.example.demo.api.user.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangePasswordRequest {
    @NotEmpty(message = "UsernameOrEmail not empty")
    private String usernameOrEmail;
    @NotEmpty(message = "Password not empty")
    @Size(min = 4, max = 50, message
            = "Password must be between 10 and 200 characters")
    private String password;

    private String token;
}
