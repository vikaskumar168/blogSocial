package com.vikas.social.service;

import com.vikas.social.dao.UserRepository;
import com.vikas.social.entity.User;
import com.vikas.social.error.ErrorDetail;
import com.vikas.social.pojos.*;
import com.vikas.social.utils.JwtUtils;
import com.vikas.social.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Service
@Slf4j
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
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

    public UserLoginResponse loginUser(UserLoginRequest loginRequest) {
        try {
            log.debug("loginUser:: UserLoginRequest: {}", loginRequest);

            // 1. Find User by Email
            User user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);

            if (user == null) {
                log.warn("Login failed: Email '{}' not found.", loginRequest.getEmail());
                throw new RuntimeException("Error: Invalid credentials");
            }

            // 2. Verify Password
            // ⚠️ CRITICAL: Currently checking plain text because 'registerUser' saves plain text.
            // In the future, switch to: passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                log.warn("Login failed: Invalid password for email '{}'", loginRequest.getEmail());
                throw new RuntimeException("Error: Invalid credentials");
            }

            // 3. Generate JWT Token
            String token = jwtUtils.generateToken(user.getUserName());

            log.info("User logged in successfully: {}", user.getUserId());

            // 4. Return Success Response
            return UserLoginResponse.builder()
                    .userId(user.getUserId())
                    .accessToken(token)
                    .build();

        } catch (Exception e) {
            log.error(StringUtils.ERROR_STR, e.getClass(), e.getLocalizedMessage(), e);
            return UserLoginResponse.builder()
                    .errorDetailList(Collections.singletonList(ErrorDetail.INTERNAL_SERVER_ERROR))
                    .build();
        }
    }
}
