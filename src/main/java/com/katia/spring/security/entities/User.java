package com.katia.spring.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
//@JsonIgnoreProperties(ignoreUnknown = false)
//@Proxy(lazy = false)
@JsonIgnoreProperties
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    @NotBlank(message = "Name is mandatory")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "Name is mandatory")
    private String lastName;
    @Column(name = "age")
    private String age;
    @Column(name = "email")
    @Email
    @NotBlank(message = "Email should be valid")
    private String email;
    @Column(name = "password")
    @NotBlank(message = "Enter the password, please")
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();

    public String getRolesAsString(Set<Role> roles) {
        List<String> roleNames = new ArrayList<>();
        if (roles == null || roles.isEmpty()) {
            return ("no roles found");
        } else {
            for (Role role : roles) {
                roleNames.add(role.getRoleName());
            }
        }
        return String.join(" ", roleNames);
    }
}
