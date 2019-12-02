package com.tuanphan.phucloctho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RoleDto {
    @NotBlank
    private String name;
    private String description;
}
