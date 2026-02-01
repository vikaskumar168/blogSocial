package com.vikas.social.service;

import com.vikas.social.dao.UserRepository;
import com.vikas.social.entity.User;
import com.vikas.social.error.ErrorDetail;
import com.vikas.social.pojos.AccountStatus;
import com.vikas.social.pojos.RegisterUserRequest;
import com.vikas.social.pojos.RegisterUserResponse;
import com.vikas.social.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
@Slf4j
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {

        try {
            log.debug("registerUser:: RegisterUserRequest: {}",registerUserRequest);
            // Check if Username exists
            if (userRepository.existsByUserName(registerUserRequest.getUserName())) {
                log.warn("Registration failed: Username '{}' already taken.", registerUserRequest.getUserName());
                throw new RuntimeException("Error: Username is already taken!");
            }

            // Check if Email exists
            if (registerUserRequest.getEmail() != null && userRepository.existsByEmail(registerUserRequest.getEmail())) {
                log.warn("Registration failed: Email '{}' already in use.", registerUserRequest.getEmail());
                throw new RuntimeException("Error: Email is already in use!");
            }

            AccountStatus status = AccountStatus.PUBLIC;
            if (registerUserRequest.getStatus() != null) {
                try {
                    status = AccountStatus.valueOf(registerUserRequest.getStatus().toUpperCase());
                } catch (IllegalArgumentException e) {
                    log.info("Invalid status '{}' provided, defaulting to PUBLIC", registerUserRequest.getStatus());
                }
            }

            User user = User.builder()
                    .firstName(registerUserRequest.getFirstName())
                    .lastName(registerUserRequest.getLastName())
                    .userName(registerUserRequest.getUserName())
                    .userProfileUrl(registerUserRequest.getUserProfileUrl())
                    .status(status)
                    .email(registerUserRequest.getEmail())
                    .password(registerUserRequest.getPassword())
                    .build();

            User savedUser = userRepository.save(user);
            log.info("User registered successfully with ID: {}", savedUser.getUserId());

            return RegisterUserResponse.builder()
                    .userId(savedUser.getUserId())
                    .build();
        } catch (Exception e) {
            log.error(StringUtils.ERROR_STR, e.getClass(), e.getLocalizedMessage(), e);
            return RegisterUserResponse.builder().errorDetailList(Collections.singletonList(ErrorDetail.INTERNAL_SERVER_ERROR)).build();
        }

    }
}
