package com.glory.Authentication.utlis;

import lombok.Getter;

@Getter
public enum Response {

    SUCCESS("200", "successfully"),
    FAILED("404", "Unsuccessful transaction"),
    EMAIL_ALREADY_EXIST("404", "Unsuccessful transaction");


    private String responseCode;

    private String responseMessage;
    Response(String responseCode, String responseMessage) {
    }
}
