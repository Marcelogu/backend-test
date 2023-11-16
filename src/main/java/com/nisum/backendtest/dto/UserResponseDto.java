package com.nisum.backendtest.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private String id;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
}
