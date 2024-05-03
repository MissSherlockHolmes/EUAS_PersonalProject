package com.MissSherlockHolmes.Farmers.Market.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.MissSherlockHolmes.Farmers.Market.repository.UserRepository;
import com.MissSherlockHolmes.Farmers.Market.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id: " + userId));
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User not found with username: " + username));
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("User not found with email: " + email));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public boolean deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete the user with id: " + userId, e);
        }
    }

    public User updateUser(Long userId, User user) {
        return user;
    }
}
