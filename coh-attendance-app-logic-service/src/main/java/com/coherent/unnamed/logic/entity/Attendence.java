package com.coherent.unnamed.logic.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="attendence")
public class Attendence {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id_fk", referencedColumnName = "id")
    private Users users;

    @Column(name = "hours")
    private long hours;

    @Column(name = "is_present")
    private int is_present;

    @Column(name = "is_delete")
    private int is_delete;

    @Column(name = "is_active")
    private int is_active;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "modified_by")
    private String modified_by;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "modified_at")
    private Timestamp modified_at;

}
