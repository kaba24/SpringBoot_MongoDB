package com.example.usersData.controller;


import com.example.usersData.model.User;
import  com.example.usersData.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private  final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        List<User> savedUsers = userService.createUsers(users);
        return ResponseEntity.ok(savedUsers);
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<User> getUserByName(@PathVariable String firstName) {
        User user = userService.getUserByName(firstName);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // Get users by email
    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> getUsersByEmail(@PathVariable String email) {
        List<User> users = userService.getUsersByEmail(email);
        return ResponseEntity.ok(users);
    }

    // Get users by age
    @GetMapping("/age/{age}")
    public ResponseEntity<List<User>> getUsersByAge(@PathVariable int age) {
        List<User> users = userService.getUsersByAge(age);
        return ResponseEntity.ok(users);
    }

    // Get users older than a certain age
    @GetMapping("/age/greater-than/{age}")
    public ResponseEntity<List<User>> getUsersOlderThan(@PathVariable int age) {
        List<User> users = userService.getUsersOlderThan(age);
        return ResponseEntity.ok(users);
    }

    // Get users by name and email
    @GetMapping("/search")
    public ResponseEntity<List<User>> getUsersByNameAndEmail(
            @RequestParam String firstName,
            @RequestParam String email) {
        List<User> users = userService.getUsersByNameAndEmail(firstName, email);
        return ResponseEntity.ok(users);
    }

    // Get users by name and age
    @GetMapping("/search/age")
    public ResponseEntity<List<User>> getUsersByNameAndAge(
            @RequestParam String firstName,
            @RequestParam int age) {
        List<User> users = userService.getUsersByNameAndAge(firstName, age);
        return ResponseEntity.ok(users);
    }

    // Get users by name, email, or age
    @GetMapping("/search/or")
    public ResponseEntity<List<User>> getUsersByNameOrEmailOrAge(
            @RequestParam String firstName,
            @RequestParam String email,
            @RequestParam int age) {
        List<User> users = userService.getUsersByNameOrEmailOrAge(firstName, email, age);
        return ResponseEntity.ok(users);
    }

    // Get paginated users
    @GetMapping("/paginated")
    public Page<User> getPaginatedUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsers(page, size);
    }

    // Get paginated users by name
    @GetMapping("/firstName/{firstName}/paginated")
    public Page<User> getPaginatedUsersByName(
            @PathVariable String firstName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsersByName(firstName, page, size);
    }

    // Get paginated users by email
    @GetMapping("/email/{email}/paginated")
    public Page<User> getPaginatedUsersByEmail(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsersByEmail(email, page, size);
    }

    // Get paginated users by age
    @GetMapping("/age/{age}/paginated")
    public Page<User> getPaginatedUsersByAge(
            @PathVariable int age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsersByAge(age, page, size);
    }

    // Get paginated users older than a specified age
    @GetMapping("/age/greater-than/{age}/paginated")
    public Page<User> getPaginatedUsersOlderThan(
            @PathVariable int age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsersOlderThan(age, page, size);
    }

    // Get paginated users by name and age
    @GetMapping("/search/firstName-age/paginated")
    public Page<User> getPaginatedUsersByNameAndAge(
            @RequestParam String firstName,
            @RequestParam int age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsersByNameAndAge(firstName, age, page, size);
    }

    // Get paginated users by name or email
    @GetMapping("/search/or/paginated")
    public Page<User> getPaginatedUsersByNameOrEmail(
            @RequestParam String firstName,
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsersByNameOrEmail(firstName, email, page, size);
    }

    // Get paginated users by name, email, or age
    @GetMapping("/search/or-age/paginated")
    public Page<User> getPaginatedUsersByNameOrEmailOrAge(
            @RequestParam String firstName,
            @RequestParam String email,
            @RequestParam int age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getPaginatedUsersByNameOrEmailOrAge(firstName, email, age, page, size);
    }

    // Get users by city
    @GetMapping("/city/{city}")
    public List<User> getUsersByCity(@PathVariable String city) {
        return userService.getUsersByCity(city);
    }

    // Get users by street
    @GetMapping("/street/{street}")
    public List<User> getUsersByStreet(@PathVariable String street) {
        return userService.getUsersByStreet(street);
    }

    // Get users by zip code
    @GetMapping("/zipcode/{zipCode}")
    public List<User> getUsersByZipCode(@PathVariable String zipCode) {
        return userService.getUsersByZipCode(zipCode);
    }

    // Get users by tags
    @GetMapping("/tags")
    public ResponseEntity<List<User>> getUsersByTags(@RequestParam List<String> tags) {
        List<User> users = userService.getUsersByTags(tags);
        return ResponseEntity.ok(users);
    }

    // Endpoint to search users by name
    @GetMapping("/search/firstName")
    public ResponseEntity<List<User>> searchUsersByName(@RequestParam String firstName) {
        List<User> users = userService.findUsersByName(firstName);
        return ResponseEntity.ok(users);
    }

    // Endpoint to search users by email
    @GetMapping("/search/email")
    public ResponseEntity<List<User>> searchUsersByEmail(@RequestParam String email) {
        List<User> users = userService.findUsersByEmail(email);
        return ResponseEntity.ok(users);
    }

    // Endpoint to search users by tag
    @GetMapping("/search/tag")
    public ResponseEntity<List<User>> searchUsersByTag(@RequestParam String tag) {
        List<User> users = userService.findUsersByTag(tag);
        return ResponseEntity.ok(users);
    }

    // Endpoint to search users by name starting with
    @GetMapping("/search/firstName/starts-with")
    public ResponseEntity<List<User>> searchUsersByNameStartingWith(@RequestParam String firstName) {
        List<User> users = userService.findUsersByNameStartingWith(firstName);
        return ResponseEntity.ok(users);
    }

    // Endpoint to search users by email starting with
    @GetMapping("/search/email/starts-with")
    public ResponseEntity<List<User>> searchUsersByEmailStartingWith(@RequestParam String email) {
        List<User> users = userService.findUsersByEmailStartingWith(email);
        return ResponseEntity.ok(users);
    }

    // Endpoint to search users by tag starting with
    @GetMapping("/search/tag/starts-with")
    public ResponseEntity<List<User>> searchUsersByTagStartingWith(@RequestParam String tag) {
        List<User> users = userService.findUsersByTagStartingWith(tag);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/last-name/{lastName}")
    public List<User> getUsersByLastName(@PathVariable String lastName) {
        return userService.getUsersByLastName(lastName);
    }

    @GetMapping("/name")
    public List<User> getUsersByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUsersByFirstAndLastName(firstName, lastName);
    }

    @GetMapping("/search/tags")
    public ResponseEntity<List<User>> getUsersByRoleAndLevel(
            @RequestParam String role,
            @RequestParam String level) {
        List<User> users = userService.findByRoleAndLevel(role, level);
        return ResponseEntity.ok(users);
    }


}
