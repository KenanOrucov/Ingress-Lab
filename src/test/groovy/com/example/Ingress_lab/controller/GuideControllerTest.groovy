package com.example.Ingress_lab.controller

import com.example.Ingress_lab.exception.ErrorHandler
import com.example.Ingress_lab.model.request.GuideRequest
import com.example.Ingress_lab.model.request.PassportRequest
import com.example.Ingress_lab.model.response.GuideResponse
import com.example.Ingress_lab.model.response.PassportResponse
import com.example.Ingress_lab.service.abstraction.GuideService
import groovy.util.logging.Slf4j
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Slf4j
class GuideControllerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private GuideService guideService
    private MockMvc mockMvc

    void setup() {
        guideService = Mock()
        def guideController = new GuideController(guideService)
        mockMvc = MockMvcBuilders.standaloneSetup(guideController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestGetAllGuides"() {
        given:
        def url = "/v1/guides"
        def passport = random.nextObject(PassportResponse)
        def guideResponse = random.nextObject(GuideResponse, "passport")
        guideResponse.passport = passport

        def responseDto = """
                              [
                                {
                                  "id": $guideResponse.id,
                                  "name": "$guideResponse.name",
                                  "email": "$guideResponse.email",
                                  "phoneNumber": "$guideResponse.phoneNumber",
                                  "status": "$guideResponse.status",
                                  "createdAt": [$guideResponse.createdAt.year,
                                                $guideResponse.createdAt.monthValue,
                                                $guideResponse.createdAt.dayOfMonth,
                                                $guideResponse.createdAt.hour,
                                                $guideResponse.createdAt.minute,
                                                $guideResponse.createdAt.second],
                                                
                                  "updatedAt": [$guideResponse.updatedAt.year,
                                                $guideResponse.updatedAt.monthValue,
                                                $guideResponse.updatedAt.dayOfMonth,
                                                $guideResponse.updatedAt.hour,
                                                $guideResponse.updatedAt.minute,
                                                $guideResponse.updatedAt.second],
                                  "passport": {
                                    "id": $passport.id,
                                    "passportNumber": "$passport.passportNumber",
                                    "issueDate": [$passport.issueDate.year,
                                                $passport.issueDate.monthValue,
                                                $passport.issueDate.dayOfMonth],
                                                
                                    "expiryDate": [$passport.expiryDate.year,
                                                $passport.expiryDate.monthValue,
                                                $passport.expiryDate.dayOfMonth],
                                                
                                    "country": "$passport.country",
                                    "passportEntityStatus": "$passport.passportEntityStatus",
                                    "createdAt": [$passport.createdAt.year,
                                                $passport.createdAt.monthValue,
                                                $passport.createdAt.dayOfMonth,
                                                $passport.createdAt.hour,
                                                $passport.createdAt.minute,
                                                $passport.createdAt.second],
                                                
                                  "updatedAt": [$passport.updatedAt.year,
                                                $passport.updatedAt.monthValue,
                                                $passport.updatedAt.dayOfMonth,
                                                $passport.updatedAt.hour,
                                                $passport.updatedAt.minute,
                                                $passport.updatedAt.second]
                                  }
                                }
                                ]
                                """

        when:
        def jsonResponse = mockMvc.perform(
                get(url)
                        .contentType(MediaType.APPLICATION_JSON)
        )

        then:
        1 * guideService.getAllGuides() >> [guideResponse]


        jsonResponse.andExpectAll(
                content().json(responseDto, true),
                status().isOk()
        )
    }

    def "TestGetGuideById"() {
        given:
        def id = random.nextObject(Long)
        def url = "/v1/guides/$id"
        def passport = random.nextObject(PassportResponse)
        def guideResponse = random.nextObject(GuideResponse, "passport")
        guideResponse.passport = passport

        def responseDto = """
                                {
                                  "id": $guideResponse.id,
                                  "name": "$guideResponse.name",
                                  "email": "$guideResponse.email",
                                  "phoneNumber": "$guideResponse.phoneNumber",
                                  "status": "$guideResponse.status",
                                  "createdAt": [$guideResponse.createdAt.year,
                                                $guideResponse.createdAt.monthValue,
                                                $guideResponse.createdAt.dayOfMonth,
                                                $guideResponse.createdAt.hour,
                                                $guideResponse.createdAt.minute,
                                                $guideResponse.createdAt.second],
                                                
                                  "updatedAt": [$guideResponse.updatedAt.year,
                                                $guideResponse.updatedAt.monthValue,
                                                $guideResponse.updatedAt.dayOfMonth,
                                                $guideResponse.updatedAt.hour,
                                                $guideResponse.updatedAt.minute,
                                                $guideResponse.updatedAt.second],
                                  "passport": {
                                    "id": $passport.id,
                                    "passportNumber": "$passport.passportNumber",
                                    "issueDate": [$passport.issueDate.year,
                                                $passport.issueDate.monthValue,
                                                $passport.issueDate.dayOfMonth],
                                                
                                    "expiryDate": [$passport.expiryDate.year,
                                                $passport.expiryDate.monthValue,
                                                $passport.expiryDate.dayOfMonth],
                                    "country": "$passport.country",
                                    "passportEntityStatus": "$passport.passportEntityStatus",
                                    "createdAt": [$passport.createdAt.year,
                                                $passport.createdAt.monthValue,
                                                $passport.createdAt.dayOfMonth,
                                                $passport.createdAt.hour,
                                                $passport.createdAt.minute,
                                                $passport.createdAt.second],
                                                
                                  "updatedAt": [$passport.updatedAt.year,
                                                $passport.updatedAt.monthValue,
                                                $passport.updatedAt.dayOfMonth,
                                                $passport.updatedAt.hour,
                                                $passport.updatedAt.minute,
                                                $passport.updatedAt.second]
                                  }
                                }
                                """


        when:
        def jsonResponse = mockMvc.perform(
                 get(url)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()

        then:
        1 * guideService.getGuideById(id) >> guideResponse


        jsonResponse.response.status == OK.value()
        JSONAssert.assertEquals(responseDto.toString(), jsonResponse.response.contentAsString.toString(), true)

    }

    def "TestCreateGuide"(){
        given:
        def url = "/v1/guides"
        def passport = random.nextObject(PassportRequest)
        def guideRequest = random.nextObject(GuideRequest, "passport")
        guideRequest.passport = passport

        def jsonRequest = """
                                {
                                  "name": "$guideRequest.name",
                                  "email": "$guideRequest.email",
                                  "phoneNumber": "$guideRequest.phoneNumber",
                                  "passport": {
                                    "passportNumber": "$passport.passportNumber",
                                    "issueDate": "$passport.issueDate",
                                    "expiryDate": "$passport.expiryDate",
                                    "country": "$passport.country"
                                  }
                                }
                                """

        when:
        def result = mockMvc.perform(
                post(url)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()

        then:
        1 * guideService.createGuide(guideRequest)

        def response = result.response
        response.status == HttpStatus.CREATED.value()

    }

    def "TestUpdateGuide"(){
        given:
        def id = random.nextObject(Long)
        def url = "/v1/guides/$id"
        def passport = random.nextObject(PassportRequest)
        def guideRequest = random.nextObject(GuideRequest, "passport")
        guideRequest.passport = passport

        def jsonRequest = """
                                {
                                  "name": "$guideRequest.name",
                                  "email": "$guideRequest.email",
                                  "phoneNumber": "$guideRequest.phoneNumber",
                                  "passport": {
                                    "passportNumber": "$passport.passportNumber",
                                    "issueDate": "$passport.issueDate",
                                    "expiryDate": "$passport.expiryDate",
                                    "country": "$passport.country"
                                  }
                                }
                                """

        when:
        def result = mockMvc.perform(
                put(url)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()

        then:
        1 * guideService.updateGuide(id, guideRequest)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()

    }

    def "TestDeleteGuide"(){
        given:
        def id = random.nextObject(Long)
        def url = "/v1/guides/$id"

        when:
        def result = mockMvc.perform(
                delete(url).contentType(MediaType.APPLICATION_JSON)
        ).andReturn()

        then:
        1 * guideService.deleteGuide(id)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }
}

