package com.katia.spring.security.model;

import com.katia.spring.security.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String age;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private List<String> roleName;
    @NotBlank
    private Set<Role> roles;

    public UserDTO (Long id, String email, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public UserDTO (Long id, String email, List<String> roleName) {
        this.id = id;
        this.email = email;
        this.roleName = roleName;
    }
}
