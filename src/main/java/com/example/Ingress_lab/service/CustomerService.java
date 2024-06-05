package com.example.Ingress_lab.service;

import com.example.Ingress_lab.dao.CustomerEntity;
import com.example.Ingress_lab.dao.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerEntity customer;
    private final UserService userService;

    public CustomerEntity fillCustomerData() {
        customer.setId(1L);
        customer.setCompany("samsung");
        customer.setUser(userService.getUser());
        return customer;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }
}
