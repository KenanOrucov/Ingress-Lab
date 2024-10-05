package com.example.Ingress_lab.service

import com.example.Ingress_lab.dao.entity.GuideEntity
import com.example.Ingress_lab.exception.NotFoundException
import com.example.Ingress_lab.model.request.GuideRequest
import com.example.Ingress_lab.model.request.PassportRequest

import static com.example.Ingress_lab.mapper.GuideMapper.toGuideEntity;
import com.example.Ingress_lab.dao.repository.GuideRepository

import static com.example.Ingress_lab.mapper.GuideMapper.updateGuideEntity
import static com.example.Ingress_lab.mapper.PassportMapper.toPassportEntity;
import com.example.Ingress_lab.service.concrete.GuideServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom

import static com.example.Ingress_lab.mapper.PassportMapper.updatePassportEntity

import spock.lang.Specification

import static com.example.Ingress_lab.model.enums.GuideStatus.INACTIVE
import static com.example.Ingress_lab.model.enums.GuideStatus.BUSY

class GuideServiceHandlerTest extends Specification{
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    GuideRepository guideRepository
    GuideServiceHandler guideServiceHandler

    def setup(){
        guideRepository = Mock()
        guideServiceHandler = new GuideServiceHandler(guideRepository)
    }

    def "TestGetAllGuides"(){
        given:
        def guide = random.nextObject(GuideEntity)

        when:
         guideServiceHandler.getAllGuides()

        then:
        1 * guideRepository.findAllByStatusIsNot(INACTIVE) >> List.of(guide)

    }

    def "TestGetGuideById() success case"(){
        given:
        def id = random.nextObject(Long)
        def guide = random.nextObject(GuideEntity)
        guide.status = BUSY

        when:
        def actual = guideServiceHandler.getGuideById(id)

        then:
        1 * guideRepository.findById(id) >> Optional.of(guide)

        actual.name == guide.name
        actual.email == guide.email
        actual.phoneNumber == guide.phoneNumber
        actual.status == guide.status
        actual.createdAt == guide.createdAt
        actual.updatedAt == guide.updatedAt

    }

    def "TestGetGuideById() not found case"(){
        given:
        def id = random.nextObject(Long)

        when:
        guideServiceHandler.getGuideById(id)

        then:
        1 * guideRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "GUIDE_NOT_FOUND"
        ex.code == "Guide not found."

    }

    def "TestGetGuideById() not found case cuz of inactive"(){
        given:
        def id = random.nextObject(Long)
        def guide = random.nextObject(GuideEntity, "status")
        guide.status = INACTIVE

        when:
        def actual = guideServiceHandler.getGuideById(id)

        then:
        1 * guideRepository.findById(id) >> Optional.of(guide)

        NotFoundException ex = thrown()
        ex.message == "GUIDE_NOT_FOUND"
        ex.code == "Guide not found."
    }

    def "TestCreateGuide()"() {
        given:
        def guideRequest = random.nextObject(GuideRequest)
        def guide = toGuideEntity(guideRequest)
        def passport = toPassportEntity(guide, guideRequest.getPassport())

        when:
        guideServiceHandler.createGuide(guideRequest)

        then:
        1* guideRepository.save(guide)

        guide.setPassport(passport)
    }

    def "TestUpdateGuide() success case"() {
        given:
        def id = random.nextObject(Long)
        def passportRequest = random.nextObject(PassportRequest)
        def guideRequest = random.nextObject(GuideRequest, "passport")
        guideRequest.passport = passportRequest
        def guideEntity = random.nextObject(GuideEntity)

        when:
        guideServiceHandler.updateGuide(id, guideRequest)

        then:
        1 * guideRepository.findById(id) >> Optional.of(guideEntity)
        1 * guideRepository.save(guideEntity)
    }

    def "TestUpdateGuide() not found case"() {
        given:
        def id = random.nextObject(Long)
        def passportRequest = random.nextObject(PassportRequest)
        def guideRequest = random.nextObject(GuideRequest, "passport")
        guideRequest.passport = passportRequest

        when:
        guideServiceHandler.updateGuide(id, guideRequest)

        then:
        1 * guideRepository.findById(id) >> Optional.empty()
        0 * guideRepository.save()

        NotFoundException ex = thrown()
        ex.message == "GUIDE_NOT_FOUND"
        ex.code == "Guide not found."
    }

    def "TestUpdateGuide() not found case cuz of inactive"() {
        given:
        def id = random.nextObject(Long)
        def passportRequest = random.nextObject(PassportRequest)
        def guideRequest = random.nextObject(GuideRequest, "passport")
        guideRequest.passport = passportRequest
        def guideEntity = random.nextObject(GuideEntity, "status")
        guideEntity.status = INACTIVE

        when:
        guideServiceHandler.updateGuide(id, guideRequest)

        then:
        1 * guideRepository.findById(id) >> Optional.of(guideEntity)
        0 * guideRepository.save()

        NotFoundException ex = thrown()
        ex.message == "GUIDE_NOT_FOUND"
        ex.code == "Guide not found."
    }

    def "TestDeleteGuide() success case"(){
        given:
        def id = random.nextObject(Long)
        def guide = random.nextObject(GuideEntity)
        guide.status = BUSY

        when:
        guideServiceHandler.deleteGuide(id)

        then:
        1 * guideRepository.findById(id) >> Optional.of(guide)
        1 * guideRepository.save(guide)

        guide.setStatus(INACTIVE)
    }

    def "TestDeleteGuide() not found case"(){
        given:
        def id = random.nextObject(Long)

        when:
        guideServiceHandler.deleteGuide(id)

        then:
        1 * guideRepository.findById(id) >> Optional.empty()
        0 * guideRepository.save()

        NotFoundException ex = thrown()
        ex.message == "GUIDE_NOT_FOUND"
        ex.code == "Guide not found."

    }

    def "TestDeleteGuide() not found case cuz of inactive"(){
        given:
        def id = random.nextObject(Long)
        def guide = random.nextObject(GuideEntity)
        guide.setStatus(INACTIVE)

        when:
        guideServiceHandler.deleteGuide(id)

        then:
        1 * guideRepository.findById(id) >> Optional.of(guide)
        0 * guideRepository.save()

        NotFoundException ex = thrown()
        ex.message == "GUIDE_NOT_FOUND"
        ex.code == "Guide not found."

    }


}
