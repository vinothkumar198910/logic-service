package com.coherent.unnamed.logic.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="timelogs")
public class TimeLogs {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id_fk", referencedColumnName = "id")
    private Users users;

    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "is_active")
    private int is_active;
    @Column(name = "is_delete")
    private int is_delete;
    @Column(name="is_logged")
    private int is_logged;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "modified_by")
    private String modified_by;

    @Column(name = "createdat")
    private Timestamp createdat;
    @Column(name = "modified_at")
    private Timestamp modified_at;
}
