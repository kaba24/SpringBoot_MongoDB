package com.example.usersData.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users") // Specifies the name of the MongoDB collection
public class User {

    @Id // Indicates the primary key
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    @Indexed // Optional: to make the query on this field faster
    private List<Tag> tags; // Array field
    private Address address;
    @Transient // This field will not be persisted in the database
    private String fullName;

    // Constructors
    public User() {}

    public User(String firstName, String lastName, String email, int age, Address address, List<Tag> tags) {
        this.firstName = firstName;
        this.email = email;
        this.age = age;
        this.address = address;
        this.tags = tags;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}