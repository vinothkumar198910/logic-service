package com.coherent.unnamed.logic.dto;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class UsersDTO {


    private int id;
    private String emailid;
    private String fullname;
    private int otp;

    private int is_active;
    private int deleted_flag;

    private String created_by;
    private String modified_by;

    private Timestamp created_at;
    private Timestamp modified_at;

}
