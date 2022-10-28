package com.springboot.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    private String id;
    private String name;
    private String password;
}
