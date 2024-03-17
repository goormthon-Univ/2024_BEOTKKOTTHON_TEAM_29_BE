package com.goormthon.tomado.common.exception;

import com.goormthon.tomado.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseException{
    public UnauthorizedException(ErrorMessage error) {
        super(error, "[UnauthorizedException] " + error.getMessage());
    }
}