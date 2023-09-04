package com.example.barberboost.repositories;

import com.example.barberboost.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

//    Optional<UserModel> findByLogin(String login);
    UserDetails findByLogin(String login);

}
