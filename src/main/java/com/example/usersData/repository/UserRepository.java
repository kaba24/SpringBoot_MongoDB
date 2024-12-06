package com.example.usersData.repository;

import com.example.usersData.model.User;
import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.List;

@EnableReactiveMongoRepositories
public interface UserRepository extends MongoRepository<User , String> {
    User findByFirstName(String firstName);

    // Find users by email
     List<User> findByEmail(String email);


    // Find users by age
    List<User> findByAge(int age);

    // Find users by age greater than a certain value
    List<User> findByAgeGreaterThan(int age);

    List<User> findByFirstNameAndEmail(String firstName, String email);

    List<User> findByFirstNameAndAge(String firstName, int age);


    @Query("{ '$or': [ { 'firstName': ?0 }, { 'email': ?1 }, { 'age': ?2 } ] }")
    List<User> findByFirstNameOrEmailOrAge(String firstName, String email, int age);

    // Paginated search for users by name
    Page<User> findByFirstName(String firstName, Pageable pageable);

    // Paginated search for users by email
    Page<User> findByEmail(String email, Pageable pageable);

    // Paginated search for users by age
    Page<User> findByAge(int age, Pageable pageable);

    // Paginated search for users older than a specified age
    Page<User> findByAgeGreaterThan(int age, Pageable pageable);

    // Paginated search for users by name and age
    Page<User> findByFirstNameAndAge(String firstName, int age, Pageable pageable);

    // Paginated search for users by name or email
    @Query("{ '$or': [ { 'firstName': ?0 }, { 'email': ?1 } ] }")
    Page<User> findByFirstNameOrEmail(String firstName, String email, Pageable pageable);

    // Paginated search for users by name, email, or age
    @Query("{ '$or': [ { 'firstName': ?0 }, { 'email': ?1 }, { 'age': ?2 } ] }")
    Page<User> findByFirstNameOrEmailOrAge(String firstName, String email, int age, Pageable pageable);

    // Find users by city (sub-document field)
    List<User> findByAddressCity(String city);

    // Find users by street (sub-document field)
    List<User> findByAddressStreet(String street);

    // Find users by zip code (sub-document field)
    List<User> findByAddressZipCode(String zipCode);

    List<User> findByTagsIn(List<String> tags);

    // Find users by name containing the search term
    List<User> findByFirstNameContainingIgnoreCase(String firstName);

    // Find users by email containing the search term
    List<User> findByEmailContainingIgnoreCase(String email);

    // Find users by tags containing the search term
    List<User> findByTagsContainingIgnoreCase(String tag);

    // Find users by name starting with the search term
    List<User> findByFirstNameStartingWithIgnoreCase(String firstName);

    // Find users by email starting with the search term
    List<User> findByEmailStartingWithIgnoreCase(String email);

    // Find users by tags starting with the search term
    List<User> findByTagsStartingWithIgnoreCase(String tag);

    // Find users by last name
    @Query("{ 'lastName': ?0 }")
    List<User> findByLastName(String lastName);

    // Find users older than a specified age
    @Query("{ 'age': { $gt: ?0 } }")
    List<User> findUsersOlderThan(int age);

    // Find users by first name and last name
    @Query("{ 'firstName': ?0, 'lastName': ?1 }")
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByTagsRoleAndTagsLevel(String role, String level);


}
