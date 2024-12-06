package com.example.usersData.service;

import com.example.usersData.model.User;
import com.example.usersData.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Optionally, method to create multiple users
    @Transactional
    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public User getUserByName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    // Method to update a user
    public User updateUser(String id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();
            userToUpdate.setFirstName(userDetails.getFirstName());
            userToUpdate.setEmail(userDetails.getEmail());
            userToUpdate.setAge(userDetails.getAge());
            return userRepository.save(userToUpdate); // Save the updated user
        }
        return null; // or throw an exception
    }

    // Method to delete a user by ID
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true; // Successfully deleted
        }
        return false; // User not found
    }

    // Method to find users by email
    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to find users by age
    public List<User> getUsersByAge(int age) {
        return userRepository.findByAge(age);
    }

    // Method to find users older than a certain age
    public List<User> getUsersOlderThan(int age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    // Method to find users by name and email
    public List<User> getUsersByNameAndEmail(String firstName, String email) {
        return userRepository.findByFirstNameAndEmail(firstName, email);
    }

    // Method to find users by name and age
    public List<User> getUsersByNameAndAge(String firstName, int age) {
        return userRepository.findByFirstNameAndAge(firstName, age);
    }

    // Method to find users by name, email, or age
    public List<User> getUsersByNameOrEmailOrAge(String firstName, String email, int age) {
        return userRepository.findByFirstNameOrEmailOrAge(firstName, email, age);
    }

    // Get paginated users
    public Page<User> getPaginatedUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    // Get paginated users by name
    public Page<User> getPaginatedUsersByName(String firstName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByFirstName(firstName, pageable);
    }

    // Get paginated users by email
    public Page<User> getPaginatedUsersByEmail(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByEmail(email, pageable);
    }

    // Get paginated users by age
    public Page<User> getPaginatedUsersByAge(int age, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByAge(age, pageable);
    }

    // Get paginated users older than a specified age
    public Page<User> getPaginatedUsersOlderThan(int age, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByAgeGreaterThan(age, pageable);
    }

    // Get paginated users by name and age
    public Page<User> getPaginatedUsersByNameAndAge(String firstName, int age, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByFirstNameAndAge(firstName, age, pageable);
    }

    // Get paginated users by name or email
    public Page<User> getPaginatedUsersByNameOrEmail(String firstName, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByFirstNameOrEmail(firstName, email, pageable);
    }

    // Get paginated users by name, email, or age
    public Page<User> getPaginatedUsersByNameOrEmailOrAge(String firstName, String email, int age, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByFirstNameOrEmailOrAge(firstName, email, age, pageable);
    }

    // Get users by city
    public List<User> getUsersByCity(String city) {
        return userRepository.findByAddressCity(city);
    }

    // Get users by street
    public List<User> getUsersByStreet(String street) {
        return userRepository.findByAddressStreet(street);
    }

    // Get users by zip code
    public List<User> getUsersByZipCode(String zipCode) {
        return userRepository.findByAddressZipCode(zipCode);
    }

    // Fetch users by tags
    public List<User> getUsersByTags(List<String> tags) {
        return userRepository.findByTagsIn(tags);
    }

    // Method to find users by name
    public List<User> findUsersByName(String firstName) {
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    // Method to find users by email
    public List<User> findUsersByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    // Method to find users by tag
    public List<User> findUsersByTag(String tag) {
        return userRepository.findByTagsContainingIgnoreCase(tag);
    }

    // Method to find users by name starting with
    public List<User> findUsersByNameStartingWith(String firstName) {
        return userRepository.findByFirstNameStartingWithIgnoreCase(firstName);
    }

    // Method to find users by email starting with
    public List<User> findUsersByEmailStartingWith(String email) {
        return userRepository.findByEmailStartingWithIgnoreCase(email);
    }

    // Method to find users by tag starting with
    public List<User> findUsersByTagStartingWith(String tag) {
        return userRepository.findByTagsStartingWithIgnoreCase(tag);
    }

    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public List<User> getUsersByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<User> findByRoleAndLevel(String role, String level) {
        return userRepository.findByTagsRoleAndTagsLevel(role, level);
    }

}
