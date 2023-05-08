package com.katia.spring.security.services;

import com.katia.spring.security.entities.Role;
import com.katia.spring.security.entities.User;
import com.katia.spring.security.model.UserDTO;
import com.katia.spring.security.repositories.RoleRepository;
import com.katia.spring.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {return userRepository.findAll();}
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElse(null);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    // --------------------------CREATE----------------------------------------

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByEmailAndIdNot(String email, Long id)  {
        return userRepository.existsByEmailAndIdNot(email, id);
    };

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        List<Role> roles = roleRepository.findByRoleNameIn(userDTO.getRoleName());
        user.getRoles().addAll(roles);

        return userRepository.save(user);
    }

    // --------------------------UPDATE----------------------------------------


    public boolean isEmailTakenByOtherUser(String email, Long id) {
        return userRepository.existsByEmailAndIdNot(email, id);
    }

    public void updateUser(Long userId, UserDTO userDTO) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        user.getRoles().clear();

        List<Role> roles = roleRepository.findByRoleNameIn(userDTO.getRoleName());
        System.out.print(userDTO.getRoleName().toString());
        System.out.print("there is a role found!");

        user.getRoles().addAll(roles);

        userRepository.save(user);
    }


    // --------------------------DELETE----------------------------------------

    public void deleteById(Long id) {

//        log.info("IN UserService deleteById {}", id);
        userRepository.deleteById(id);
    }

    // --------------------------SuccessUserHandler----------------------------------------

    public Long getCurrentUserId(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return user.getId();
    }
}
