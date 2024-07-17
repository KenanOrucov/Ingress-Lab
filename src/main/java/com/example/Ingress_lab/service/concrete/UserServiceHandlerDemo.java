package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceHandlerDemo implements UserService {

    @Override
    public void createUser(String username, String password) {
        System.out.println(username + " " + password);
    }
}
