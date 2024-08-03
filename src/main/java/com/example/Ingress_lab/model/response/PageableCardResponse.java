package com.example.Ingress_lab.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableCardResponse {
    private List<CardResponse> cards;
    private int lastPageNumber;
    private long totalElements;
    private boolean hasNextPage;
}
