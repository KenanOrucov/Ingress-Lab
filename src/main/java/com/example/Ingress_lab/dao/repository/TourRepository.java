package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.TourEntity;
import com.example.Ingress_lab.model.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends JpaRepository<TourEntity, Long> {
    List<TourEntity> findAllByTourStatus(EntityStatus tourStatus);

    @Query(value = "SELECT t.* FROM tours t LEFT JOIN  tours_travelers tt ON tt.tour_id = t.id WHERE tt.traveler_id = :travelerId and t.tour_status = :status", nativeQuery = true)
    List<TourEntity> findToursByTravelerIdAndStatus(@Param("travelerId") Long travelerId, @Param("status") String status);

    @Query(value = "SELECT t.* FROM tours t LEFT JOIN  guides_tours gt ON gt.tour_id = t.id WHERE gt.guide_id = :guideId", nativeQuery = true)
    List<TourEntity> findToursByGuideId(Long guideId);
}
