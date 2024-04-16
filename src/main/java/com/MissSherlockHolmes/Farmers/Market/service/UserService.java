//package com.MissSherlockHolmes.Farmers.Market.service;
//
//import com.MissSherlockHolmes.Farmers.Market.model.User;
//import com.MissSherlockHolmes.Farmers.Market.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    // Fetch all users
//    public List<User> findAllUsers() {
//        return userRepository.findAll();
//    }
//
//    // Find a user by ID
//    public User findUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found for id " + id));
//    }
//
//    // Update a user's details
//    public User updateUser(Long id, User userDetails) {
//        User user = findUserById(id);
//        user.setName(userDetails.getName());
//        user.setEmail(userDetails.getEmail());
//        return userRepository.save(user);
//    }
//
//    // Delete a user by ID
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    public User createUser(User newUser) {
//        return newUser;
//    }
//}
