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
public class RegisterUserResponse {

    private Long userId;
    private List<ErrorDetail> errorDetailList;

}
