package com.MissSherlockHolmes.Farmers.Market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.MissSherlockHolmes.Farmers.Market.repository.UserRepository;
import com.MissSherlockHolmes.Farmers.Market.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        // Hash the user's password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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
        // Possibly add functionality to handle password updates with re-hashing
        return userRepository.save(user); // Ensure that the repository is correctly used
    }

    public boolean validateLogin(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public boolean checkLogin(String username, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.isPresent() && passwordEncoder.matches(rawPassword, optionalUser.get().getPassword());
    }


}
