package com.coherent.unnamed.logic.repository;

import com.coherent.unnamed.logic.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {

}
