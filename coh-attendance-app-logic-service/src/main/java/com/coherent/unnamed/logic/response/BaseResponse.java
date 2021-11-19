package com.coherent.unnamed.logic.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BaseResponse<T> {

    private String StatusCode;

    private String StatusMessage;

    private T Data;
}