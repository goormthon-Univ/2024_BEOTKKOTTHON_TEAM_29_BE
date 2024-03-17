package com.goormthon.tomado.common.exception;

import com.goormthon.tomado.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {
    public NotFoundException(ErrorMessage error) {
        super(error, "[NotFoundException] "+ error.getMessage());
    }
}