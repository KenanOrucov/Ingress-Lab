package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.DestinationEntity;
import com.example.Ingress_lab.dao.entity.TourEntity;
import com.example.Ingress_lab.model.enums.Status;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long>{

    List<DestinationEntity> findAllByStatus(Status status);

    @EntityGraph(attributePaths = "tour")
    List<DestinationEntity> findByStatusAndTourId(Status status, Long tourId);
}
