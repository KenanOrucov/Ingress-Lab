package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long>{


}
