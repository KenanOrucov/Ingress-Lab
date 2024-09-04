package com.example.Ingress_lab.dao.repository;


import com.example.Ingress_lab.dao.entity.GuideEntity;
import com.example.Ingress_lab.model.enums.GuideStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuideRepository extends JpaRepository<GuideEntity, Long>, JpaSpecificationExecutor<GuideEntity> {

    @EntityGraph(attributePaths = "passport")
    List<GuideEntity> findAllByStatusIsNot(GuideStatus status);

    @EntityGraph(attributePaths = "passport")
    Optional<GuideEntity> findById(Long id);

    @Query("SELECT g FROM GuideEntity g " +
            "LEFT JOIN FETCH g.passport p " +
            "JOIN g.tours gt " +
            "WHERE gt.id = :tourId")
    List<GuideEntity> findGuidesByTourIdAndStatus(@Param("tourId") Long tourId);


    @Query("SELECT g FROM GuideEntity g " +
            "LEFT JOIN FETCH g.passport " +
            "LEFT JOIN FETCH g.tours gt " +
            "WHERE g.status != 'INACTIVE'")
    List<GuideEntity> findAvailableGuidesWithinDate();


}
