package com.vikas.social.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorDetail {

    // 1xxx system errors
    INTERNAL_SERVER_ERROR("BELL_1001", "internal.server.error", ErrorType.SYSTEM,
            "An internal server error occurred. Please try after sometime."),
    USER_NOT_AUTHENTICATED("BELL_1002", "user.not.authenticated", ErrorType.SYSTEM, "User not authenticated."),
    NOT_ALLOWED_TO_USE_APP("BELL_1003", "not.allowed.to.use.app", ErrorType.SYSTEM,
            "You are not allowed to use this app."),
    SOFT_UPGRADE_AVAILABLE("BELL_1004", "soft.upgrade.available", ErrorType.SYSTEM, "Soft upgrade available"),
    HARD_UPGRADE_REQUIRED("BELL_1005", "hard.upgrade.required", ErrorType.SYSTEM, "Hard upgrade required"),

    // 2xxx business errors
    OPERATION_NOT_ALLOWED("BELL_2001", "operation.not.allowed", ErrorType.BUSINESS,
            "Not authorized for this operation"),
    BELL_NOT_FOUND("BELL_2004", "bell.not.found", ErrorType.BUSINESS, "No bell found for given id!");

    private static final String TO_STRING_TEMPLATE = "code: %s, propertyKey: %s, errorType: %s, defaultMessage: %s";

    String code;
    String propertyKey;
    ErrorType errorType;
    String defaultMessage;

    private ErrorDetail(String code, String propertyKey, ErrorType errorType, String defaultMessage) {
        this.code = code;
        this.propertyKey = propertyKey;
        this.errorType = errorType;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, code, propertyKey, errorType, defaultMessage);
    }

    public enum ErrorType {
        SYSTEM("system"), VALIDATION("validation"), BUSINESS("business");

        String error;

        private ErrorType(String error) {
            this.error = error;
        }

        @Override
        public String toString() {
            return error;
        }
    }

    public String getCode() {
        return code;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

