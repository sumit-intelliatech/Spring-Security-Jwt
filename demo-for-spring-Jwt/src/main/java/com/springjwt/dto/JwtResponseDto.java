package com.springjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class JwtResponseDto implements Serializable {


    private String accessToken;
    private String refreshToken;


}
