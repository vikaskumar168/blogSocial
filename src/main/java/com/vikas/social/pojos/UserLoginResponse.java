package com.vikas.social.pojos;

import com.vikas.social.error.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResponse {

    private Long userId;
    private String accessToken;
    private List<ErrorDetail> errorDetailList;
}
