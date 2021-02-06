package com.company.service.user;

import com.company.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    long join(User user);
    Optional<User> findById(long id);
    List<User> searchByName(String name);
    List<User> findAllUsers();


}
