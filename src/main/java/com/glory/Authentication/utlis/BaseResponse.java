package com.glory.Authentication.utlis;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private String statusCode;

    private String message;

    private T data;
}
