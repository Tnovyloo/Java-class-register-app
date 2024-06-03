package com.example.class_register_server.repository;

import com.example.class_register_server.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public User findUserByEmail(String email) {
        User user = new User(email, "123");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        return user;
    }

    public User findByEmail(String email) {
        String nativeQuery = "SELECT * FROM user WHERE email = :email LIMIT 1";
        Query query = entityManager.createNativeQuery(nativeQuery, User.class);
        System.out.println(email);
        query.setParameter("email", email);
        
        try {
            System.out.println("XXD");

            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}


// Does not work because i have to implement repository method to create new user. (It gets called when authenticating user, but i dont have any register api call so i dont do it.)
// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//     // Method to find a user by email
//     User findUserByEmail(String email);
// }

