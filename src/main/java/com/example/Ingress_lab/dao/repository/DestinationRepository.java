package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.DestinationEntity;
import com.example.Ingress_lab.model.enums.EntityStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long>{

    List<DestinationEntity> findAllByDestinationStatus(EntityStatus destinationStatus);

    @EntityGraph(attributePaths = "tour")
    List<DestinationEntity> findByDestinationStatusAndTourId(EntityStatus destinationStatus, Long tourId);
}
