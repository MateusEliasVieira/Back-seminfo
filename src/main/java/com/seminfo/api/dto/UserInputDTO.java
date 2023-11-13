package com.seminfo.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDTO
{

    @NotBlank
    @Size(min = 4)
    @Pattern(regexp = "^[A-Z]+(.)*") // garante que a primeira letra seja maiuscula
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotBlank
    private String about;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String image;

}
