package com.coherent.unnamed.logic.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZoneId;

@Data
@Entity
@Table(name="users")
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
