package com.example.Ingress_lab.mapper

import com.example.Ingress_lab.dao.entity.GuideEntity
import com.example.Ingress_lab.model.request.GuideRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.Ingress_lab.mapper.PassportMapper.toPassportResponse
import static com.example.Ingress_lab.model.enums.GuideStatus.FREE;


class GuideMapperTest extends Specification{
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestToGuideEntity()"(){
        given:
        def dto = random.nextObject(GuideRequest)

        when:
        def entity = GuideMapper.toGuideEntity(dto)

        then:
        entity.name == dto.name
        entity.email == dto.email
        entity.phoneNumber == dto.phoneNumber
        entity.status == FREE
        entity.passport == null
        entity.tours == null
    }

    def "TestToGuideResponse()"(){
        given:
        def entity = random.nextObject(GuideEntity)

        when:
        def dto = GuideMapper.toGuideResponse(entity)

        then:
        dto.id == entity.id
        dto.name == entity.name
        dto.email == entity.email
        dto.phoneNumber == entity.phoneNumber
        dto.status == entity.status
        dto.createdAt == entity.createdAt
        dto.updatedAt == entity.updatedAt
        dto.passport == toPassportResponse(entity.getPassport())
    }

    def "TestToGuideResponses()"(){
        given:
        def dtos = random.nextObject(List<GuideRequest>)

        when:
        def entities = GuideMapper.toGuideResponses(dtos)

        then:
        entities.size() == dtos.size()
        for (int i = 0; i < entities.size(); i++) {
            entities[i].id == dtos[i].id
            entities[i].name == dtos[i].name
            entities[i].email == dtos[i].email
            entities[i].phoneNumber == dtos[i].phoneNumber
            entities[i].status == dtos[i].status
            entities[i].passport == null
        }
    }

    def "TestUpdateGuideEntity()"(){
        given:
        def entity = random.nextObject(GuideEntity)
        def dto = random.nextObject(GuideRequest)

        when:
        GuideMapper.updateGuideEntity(entity, dto)

        then:
        entity.name == dto.name
        entity.email == dto.email
        entity.phoneNumber == dto.phoneNumber
        entity.updatedAt != null
    }


}
