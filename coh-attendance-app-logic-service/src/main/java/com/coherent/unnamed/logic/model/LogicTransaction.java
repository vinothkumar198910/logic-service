package com.coherent.unnamed.logic.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "logic_transaction")
public class LogicTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

	@Column(name = "commodity")
    private String  commodity;
    
	@Column(name = "grade")
    private String grade;
    
	@Column(name = "quantity")
    private int quantity;
	
	@Column(name = "price")
    private double price;
	
	@Column(name = "total")
    private double total;
	
	@Column(name = "is_active")
    private boolean isActive;
    
	@Column(name = "deleted_flag")
    private boolean deletedFlag;
    
	@Column(name = "created_by")
    private Long createdBy;


    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    

    @Column(name = "modified_at")
    private Timestamp modifiedAt;
    
	@Column(name = "modified_by")
    private Long modifiedBy;
}
