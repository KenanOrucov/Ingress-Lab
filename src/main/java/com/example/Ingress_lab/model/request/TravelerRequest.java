package com.example.Ingress_lab.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private List<Long> tourIds;
}
