package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.model.criteria.CardCriteria;
import com.example.Ingress_lab.model.criteria.PageCriteria;
import com.example.Ingress_lab.model.request.UserRequest;
import com.example.Ingress_lab.model.response.PageableResponse;
import com.example.Ingress_lab.model.response.UserResponse;

public interface UserService {
    void createUser(UserRequest request);
    UserResponse getUserById(Long id);
    PageableResponse getAllUsers(PageCriteria pageCriteria, CardCriteria cardCriteria);
    void updateUser(Long id, UserRequest request);
    void deleteUserById(Long id);
    void clearCache();
}
