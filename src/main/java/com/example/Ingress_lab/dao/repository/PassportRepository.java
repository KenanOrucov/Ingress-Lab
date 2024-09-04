package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.PassportEntity;
import com.example.Ingress_lab.model.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PassportRepository extends JpaRepository<PassportEntity, Long>{
    List<PassportEntity> findAllByPassportStatus(EntityStatus passportStatus);

}
