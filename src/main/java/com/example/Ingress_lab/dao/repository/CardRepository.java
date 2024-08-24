package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.CardEntity;
import com.example.Ingress_lab.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long>, JpaSpecificationExecutor<CardEntity> {

    List<CardEntity> findAllByStatus(Status status);

}
