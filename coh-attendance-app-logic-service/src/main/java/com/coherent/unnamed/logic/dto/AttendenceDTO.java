package com.coherent.unnamed.logic.dto;

import com.coherent.unnamed.logic.entity.Users;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class AttendenceDTO {
    private int id;
    private long hours;
    private int is_present;
    private int is_delete;
    private int is_active;
    private String created_by;
    private String modified_by;
    private Timestamp created_at;
    private Timestamp modified_at;
    private Users users;
}
