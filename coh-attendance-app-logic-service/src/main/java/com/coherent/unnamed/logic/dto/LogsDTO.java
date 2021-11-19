package com.coherent.unnamed.logic.dto;

import com.coherent.unnamed.logic.entity.Users;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class LogsDTO {
    private String created_by;
    private Timestamp created_at;
    private int is_logged;
}
