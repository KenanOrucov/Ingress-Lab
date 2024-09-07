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
import static com.example.Ingress_lab.model.enums.ExceptionConstants.PASSPORT_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.EntityStatus.ACTIVE;
import static com.example.Ingress_lab.model.enums.EntityStatus.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceHandler implements PassportService {
    private final PassportRepository passportRepository;
    @Override
    public List<PassportResponse> getAllPassports() {
        return toPassportResponses(passportRepository.findAllByPassportStatus(ACTIVE));
    }

    @Override
    public PassportResponse getPassportById(Long id) {
        var passport = fetchPassportIfExist(id);
        return toPassportResponse(passport);
    }

    @Override
    public void createPassport(PassportRequest request) {
        var passport = toPassportEntity(null, request);
        passportRepository.save(passport);
    }

    @Override
    public void updatePassport(Long id, PassportRequest request) {
        var passport = fetchPassportIfExist(id);
        updatePassportEntity(passport, request);
        passportRepository.save(passport);
    }

    @Override
    public void deletePassport(Long id) {
        var passport = fetchPassportIfExist(id);
        passport.setPassportStatus(INACTIVE);
        passportRepository.save(passport);
    }

    @CacheEvict(allEntries = true, value = "passports")
    @Override
    public void clearCache() {

    }
    public PassportEntity fetchPassportIfExist(Long id) {
        return passportRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(PASSPORT_NOT_FOUND.getCode(), PASSPORT_NOT_FOUND.getMessage()));
    }


}
