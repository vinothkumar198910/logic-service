package com.coherent.unnamed.logic.response;

import com.coherent.unnamed.logic.dto.LogsDTO;
import com.coherent.unnamed.logic.dto.LogsbydatesdataDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
public class BaseResponseLogbydate<T> {
    private String StatusCode;

    private String StatusMessage;

    private T Data;

    private Date date;

    private long hours;

}
