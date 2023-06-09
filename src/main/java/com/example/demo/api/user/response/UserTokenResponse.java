package com.example.demo.api.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserTokenResponse {
    private String username;
    private String token;
}
