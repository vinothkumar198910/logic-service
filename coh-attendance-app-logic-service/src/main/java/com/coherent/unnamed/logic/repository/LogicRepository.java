package com.coherent.unnamed.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coherent.unnamed.logic.model.LogicTransaction;

@Repository
public interface LogicRepository extends JpaRepository<LogicTransaction, Long>{

}
