package com.vikas.social.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String userProfileUrl;
    private String status;
    private String password;
}
