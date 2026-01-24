package com.vikas.social.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    private Long userId;
    private String firstName;
    private String LastName;
    private String mediaUrl;
    private Long followersCount;
    private Long followeeCount;
    private String status; //PUBLIC OR PRIVATE


}
