package com.coherent.unnamed.logic.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class LogicTransctionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String commodity;
    private String grade;
    private int quantity;
    private double price;
    private double total;
    private boolean isActive;
    private boolean deletedFlag;
    private Long createdBy;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Long modifiedBy;
	
}
