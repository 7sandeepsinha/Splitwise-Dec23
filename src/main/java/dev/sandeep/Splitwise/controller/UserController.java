package dev.sandeep.Splitwise.controller;

import dev.sandeep.Splitwise.dto.UserRegistrationRequestDTO;
import dev.sandeep.Splitwise.entity.User;
import dev.sandeep.Splitwise.exception.UserRegistrationInvalidDataException;
import dev.sandeep.Splitwise.mapper.EntityDTOMapper;
import dev.sandeep.Splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO){
        validateUserRegistrationRequestDTO(userRegistrationRequestDTO);
        User savedUser = userService.signup(userRegistrationRequestDTO.getName(), userRegistrationRequestDTO.getEmail(), userRegistrationRequestDTO.getPassword());
        return ResponseEntity.ok(
                EntityDTOMapper.toDTO(savedUser)
        );
    }

    private void validateUserRegistrationRequestDTO(UserRegistrationRequestDTO requestDTO){
        // do both using regex
        //TODO : validate if the email is proper
        //TODO : validate if password has atleast 8 characters including a small, capital, numeric and special character
        if(requestDTO.getEmail() == null ||
        requestDTO.getName() == null ||
        requestDTO.getPassword() == null){
            throw new UserRegistrationInvalidDataException("Invalid sign up data");
        }
    }
}
