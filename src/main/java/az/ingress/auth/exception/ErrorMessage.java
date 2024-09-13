package az.ingress.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    UNEXPECTED_ERROR("Unexpected error occurred"),
    CLIENT_ERROR("Exception from client"),
    TOKEN_EXPIRED("Token expired"),
    USER_UNAUTHORIZED("User unauthorized"),
    REFRESH_TOKEN_COUNT_EXPIRED("Refresh token count expired"),
    BRUTE_FORCE_EXCEPTION("Too many requests, please try again after 5 minutes.");

    private final String message;
}
