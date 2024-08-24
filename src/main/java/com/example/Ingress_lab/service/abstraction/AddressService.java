package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.model.criteria.CardCriteria;
import com.example.Ingress_lab.model.criteria.PageCriteria;
import com.example.Ingress_lab.model.request.AddressRequest;
import com.example.Ingress_lab.model.request.UserRequest;
import com.example.Ingress_lab.model.response.AddressResponse;
import com.example.Ingress_lab.model.response.PageableResponse;
import com.example.Ingress_lab.model.response.UserResponse;

public interface AddressService {
    void createAddress(AddressRequest request);
    AddressResponse getAddressById(Long id);
    PageableResponse getAllAddresses(PageCriteria pageCriteria, CardCriteria cardCriteria);
    void updateAddress(Long id, AddressRequest request);
    void deleteAddressById(Long id);
    void clearCache();
}
