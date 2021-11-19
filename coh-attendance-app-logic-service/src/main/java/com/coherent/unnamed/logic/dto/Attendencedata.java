package com.coherent.unnamed.logic.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Attendencedata {
    private int is_present;
    private String created_by;
    private Timestamp created_at;
}
