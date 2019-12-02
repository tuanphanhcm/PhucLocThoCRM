package com.tuanphan.phucloctho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank
    @Length(min = 4, max = 32)
    private String username;

    @NotBlank
    @Length(min = 8, max = 32)
    private String password;
    private String confirmPassword;
    @NotBlank
    private String name;
    private String phone;
    private int roleId;

}

