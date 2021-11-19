package com.coherent.unnamed.logic.dto;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class TimeLogsDTO{

    private int id;
    private int is_logged;
    private Double longitude;
    private Double latitude;
    private int is_active;
    private int is_delete;
    private String created_by;
    private String modified_by;


    private Timestamp createdat;
    private Timestamp modified_at;
}
