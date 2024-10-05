package com.binhcodev.todo_list.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Email invalid")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
