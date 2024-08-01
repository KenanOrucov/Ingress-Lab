package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.CardEntity;
import com.example.Ingress_lab.model.enums.CardStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
    @Override
    List<CardEntity> findAll();

    List<CardEntity> findByStatus(CardStatus status);

}
