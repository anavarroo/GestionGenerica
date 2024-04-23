package com.api.gestiongenerica.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.gestiongenerica.persistence.model.User;

@Repository
public interface UserRepositoryI extends JpaRepository<User, Long>{
    

}
