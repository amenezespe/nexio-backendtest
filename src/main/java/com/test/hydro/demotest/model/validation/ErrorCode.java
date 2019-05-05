package com.test.hydro.demotest.model.validation;

import org.springframework.http.HttpStatus;

public enum ErrorCode {


    ID_COMMENT_ERROR("10001", "L'id du commentaire n'est pas valide."),
    DESCRIPTION_ERROR("10002", "La description du commentaire n'est pas valide."),
    INTERNAL_ERROR("99999", "Une erreur interne se produit.");


    private String errorCode;
    private String message;

    ErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
