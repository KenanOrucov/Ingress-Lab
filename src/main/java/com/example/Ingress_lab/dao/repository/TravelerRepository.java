package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.TravelerEntity;
import com.example.Ingress_lab.model.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelerRepository extends JpaRepository<TravelerEntity, Long> {
    List<TravelerEntity> findAllByTravelerStatus(EntityStatus travelerStatus);

    @Query("SELECT t FROM TravelerEntity t JOIN FETCH t.tours tours WHERE t.travelerStatus = 'ACTIVE' AND tours.id = :tourId")
    List<TravelerEntity> findByStatusAndTourId(@Param("tourId") Long tourId);
}
