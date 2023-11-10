package com.seminfo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotBlank
    @Size(min = 4)
    private String name;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    @NotBlank
    @Column(columnDefinition = "text")
    private String about;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(columnDefinition = "text")
    private String image;
    @Column(columnDefinition = "text")
    private String token;
    @Column(nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;


}
