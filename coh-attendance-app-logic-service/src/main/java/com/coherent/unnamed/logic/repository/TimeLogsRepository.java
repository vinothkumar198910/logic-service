package com.coherent.unnamed.logic.repository;

import com.coherent.unnamed.logic.entity.TimeLogs;
import com.coherent.unnamed.logic.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public interface TimeLogsRepository extends JpaRepository<TimeLogs, Integer> {


    @Query(value = "select * from timelogs where user_id_fk = :users and date(createdat) = :localDate", nativeQuery = true)
    List<TimeLogs> findByUser_id_fk(Users users, LocalDate localDate);

    @Query(value = "select * from timelogs r where r.user_id_fk= :user_id and date(r.createdat) = :meYesterday order by r.user_id_fk asc", nativeQuery = true)
    List<TimeLogs> findByUserId(int user_id, Date meYesterday);
}
