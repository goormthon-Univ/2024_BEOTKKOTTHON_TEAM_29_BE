package com.goormthon.tomado.common.exception;

import com.goormthon.tomado.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class ConflictException extends BaseException{
    public ConflictException(ErrorMessage error) {
        super(error, "[ConflictException] " + error.getMessage());
    }
}