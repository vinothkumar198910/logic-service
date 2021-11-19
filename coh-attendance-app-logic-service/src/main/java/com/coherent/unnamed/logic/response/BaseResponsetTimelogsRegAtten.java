package com.coherent.unnamed.logic.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BaseResponsetTimelogsRegAtten {
    private String StatusCode;

    private String StatusMessage;
}
