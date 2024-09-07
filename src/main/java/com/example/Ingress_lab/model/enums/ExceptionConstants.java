package com.example.Ingress_lab.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionConstants {
    DESTINATION_NOT_FOUND("DESTINATION_NOT_FOUND", "Destination not found."),
    GUIDE_NOT_FOUND("GUIDE_NOT_FOUND", "Guide not found."),
    GUIDE_BUSY("GUIDE_BUSY", "Guide is busy."),
    PASSPORT_NOT_FOUND("PASSPORT_NOT_FOUND", "Passport not found."),
    TOUR_NOT_FOUND("TOUR_NOT_FOUND", "Tour not found."),
    TRAVELER_NOT_FOUND("TRAVELER_NOT_FOUND", "Traveler not found."),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred."),
    HTTP_METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Method not allowed."),
    CLIENT_ERROR("CLIENT_ERROR", "Exception from Client");
    private final String code;
    private final String message;
}
