package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.client.PermissionClient;
import com.example.Ingress_lab.model.client.CheckPermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "permissionService")
@RequiredArgsConstructor
public class PermissionServiceHandler {
    private final PermissionClient permissionClient;

    public boolean checkPermission(String userId, String permissionName, String productName){
        boolean result;
        try {
            result = permissionClient.checkPermission(
                    new CheckPermissionDto(userId, permissionName, productName)
            );
        }catch (Exception e){
            return false;
        }
        return result;
    }
}
