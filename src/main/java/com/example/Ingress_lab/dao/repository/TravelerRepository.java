package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.TravelerEntity;
import com.example.Ingress_lab.model.enums.Status;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelerRepository extends JpaRepository<TravelerEntity, Long> {
    List<TravelerEntity> findAllByStatus(Status status);

    @Query("SELECT t FROM TravelerEntity t JOIN FETCH t.tours tours WHERE t.status = 'ACTIVE' AND tours.id = :tourId")
    List<TravelerEntity> findByStatusAndTourId(@Param("tourId") Long tourId);
}
