package com.coeus.eTap_app.repository;

import com.coeus.eTap_app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserEmail(String userEmail);
}
