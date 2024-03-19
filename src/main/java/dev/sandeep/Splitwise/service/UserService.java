package dev.sandeep.Splitwise.service;

import dev.sandeep.Splitwise.entity.User;

public interface UserService {
    User signup(String name, String email, String password);
}
