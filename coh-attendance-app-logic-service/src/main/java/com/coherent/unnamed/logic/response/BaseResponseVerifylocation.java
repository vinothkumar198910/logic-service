package com.coherent.unnamed.logic.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BaseResponseVerifylocation<T> {
    private String StatusCode;

    private String StatusMessage;

    private int inRange;

}
