package com.goormthon.tomado.common.exception;

import com.goormthon.tomado.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException{
    public BadRequestException(ErrorMessage error) {
        super(error, "[BadRequestException] " + error.getMessage());
    }
}