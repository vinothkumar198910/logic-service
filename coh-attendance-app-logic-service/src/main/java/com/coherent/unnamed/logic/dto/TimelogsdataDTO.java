package com.coherent.unnamed.logic.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TimelogsdataDTO {
    private Timestamp created_at;
    private Double longitude;
    private Double latitude;
    //private LogsbydatesdataDTO logsbydatesdataDTO;
}
