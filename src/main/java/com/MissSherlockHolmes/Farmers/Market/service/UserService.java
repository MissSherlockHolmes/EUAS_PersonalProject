package com.MissSherlockHolmes.Farmers.Market.service;

import com.MissSherlockHolmes.Farmers.Market.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.MissSherlockHolmes.Farmers.Market.model.User;
import com.MissSherlockHolmes.Farmers.Market.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor  // Correctly utilize Lombok to eliminate boilerplate constructor code
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encode and set the password
        return userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
        return true;
    }

    public User updateUser(Long userId, User updatedUserDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        updateUserData(user, updatedUserDetails);
        return userRepository.save(user);
    }

    private void updateUserData(User user, User updatedUserDetails) {
        user.setUsername(updatedUserDetails.getUsername());
        user.setEmail(updatedUserDetails.getEmail());
        user.setFirstName(updatedUserDetails.getFirstName());
        user.setSurname(updatedUserDetails.getSurname());
        user.setStreet(updatedUserDetails.getStreet());
        user.setHouse_numb(updatedUserDetails.getHouse_numb());
        user.setCity(updatedUserDetails.getCity());
        user.setRegion(updatedUserDetails.getRegion());
        user.setCountry(updatedUserDetails.getCountry());
        user.setPostal_code(updatedUserDetails.getPostal_code());
        // Check if password needs to be updated and rehashed
        if (updatedUserDetails.getPassword() != null && !updatedUserDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUserDetails.getPassword()));
        }
    }

    public UserResponseDto validateLogin(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail()))
                .orElse(null);
    }
}

