package com.example.Ingress_lab.client;

import com.example.Ingress_lab.model.client.CheckPermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "ms-permission", url = "${client.ms-permission.url}", path = "/v1/permission")
public interface PermissionClient {

    @PostMapping("/check")
    public boolean checkPermission(@RequestBody CheckPermissionDto dto);
}
