package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.PassportEntity;
import com.example.Ingress_lab.dao.repository.PassportRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.request.PassportRequest;
import com.example.Ingress_lab.model.response.PassportResponse;
import com.example.Ingress_lab.service.abstraction.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Ingress_lab.mapper.PassportMapper.toPassportEntity;
import static com.example.Ingress_lab.mapper.PassportMapper.toPassportResponse;
import static com.example.Ingress_lab.mapper.PassportMapper.toPassportResponses;
import static com.example.Ingress_lab.mapper.PassportMapper.updatePassportEntity;
import static com.example.Ingress_lab.model.enums.ExceptionMessages.PASSPORT_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.EntityStatus.ACTIVE;
import static com.example.Ingress_lab.model.enums.EntityStatus.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceHandler implements PassportService {
    private final PassportRepository passportRepository;
    @Override
    public List<PassportResponse> getAllPassports() {
        log.info("ActionLog.getAllPassports.start");
        return toPassportResponses(passportRepository.findAllByPassportStatus(ACTIVE));
    }

    @Override
    public PassportResponse getPassportById(Long id) {
        log.info("ActionLog.getPassportById.start id: {}", id);
        var passport = fetchPassportIfExist(id);
        log.info("ActionLog.getPassportById.success id: {}", id);
        return toPassportResponse(passport);
    }

    @Override
    public void createPassport(PassportRequest request) {
        log.info("ActionLog.createPassport.start request: {}", request);
        var passport = toPassportEntity(null, request);
        log.info("ActionLog.createPassport.success passport: {}", passport);
        passportRepository.save(passport);
    }

    @Override
    public void updatePassport(Long id, PassportRequest request) {
        log.info("ActionLog.updatePassport.start id: {}, request: {}", id, request);
        var passport = fetchPassportIfExist(id);
        updatePassportEntity(passport, request);
        passportRepository.save(passport);
        log.info("ActionLog.updatePassport.success id: {}", id);
    }

    @Override
    public void deletePassport(Long id) {
        log.info("ActionLog.deletePassport.start id: {}", id);
        var passport = fetchPassportIfExist(id);
        passport.setPassportStatus(INACTIVE);
        log.info("ActionLog.deletePassport.success id: {}", id);
        passportRepository.save(passport);
    }

    @CacheEvict(allEntries = true, value = "passports")
    @Override
    public void clearCache() {
        log.info("ActionLog.clearCache.success");
    }
    public PassportEntity fetchPassportIfExist(Long id) {
        return passportRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(PASSPORT_NOT_FOUND.getCode(), PASSPORT_NOT_FOUND.getMessage()));
    }


}
