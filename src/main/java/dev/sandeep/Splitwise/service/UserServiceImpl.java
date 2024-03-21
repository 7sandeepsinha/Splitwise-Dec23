package dev.sandeep.Splitwise.service;

import dev.sandeep.Splitwise.entity.User;
import dev.sandeep.Splitwise.exception.InvalidCredentialException;
import dev.sandeep.Splitwise.exception.UserDoesNotExistException;
import dev.sandeep.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User signup(String name, String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(name);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser == null){
            throw new UserDoesNotExistException("User not Found");
        }
        if(encoder.matches(password, savedUser.getPassword())){
            return savedUser;
        } else {
            throw new InvalidCredentialException("Invalid credentials");
        }
    }
}
