package com.vikas.social.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Followers {

    private Long Id;
    private Long followerId;
    private Long followeeId;
}
