package dev.sandeep.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDTO {
    private String name;
    private String email;
    private String password;
}
