package com.coherent.unnamed.logic.dto;

import com.coherent.unnamed.logic.entity.TimeLogs;
import com.coherent.unnamed.logic.entity.Users;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class LogsbydatesdataDTO {

    private Date date;
    private int hours;
    private LogsDTO logs;


}
