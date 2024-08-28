package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.PassportEntity;
import com.example.Ingress_lab.dao.entity.TravelerEntity;
import com.example.Ingress_lab.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PassportRepository extends JpaRepository<PassportEntity, Long>{
    List<PassportEntity> findAllByStatus(Status status);

}
