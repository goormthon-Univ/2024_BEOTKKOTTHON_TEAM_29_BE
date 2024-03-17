package com.goormthon.tomado.common.exception;

import com.goormthon.tomado.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class ForbiddenException extends BaseException{
    public ForbiddenException(ErrorMessage error) {
        super(error, "[ForbiddenException] " + error.getMessage());
    }
}