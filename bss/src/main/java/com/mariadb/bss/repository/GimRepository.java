package com.mariadb.bss.repository;

import com.mariadb.bss.entity.Gim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface GimRepository extends JpaRepository<Gim, String> {
    @Query(value = "select count(GIMID) from GIM", nativeQuery = true)
    long countGimID();


    @Query(value = "select VOLTAGE from GIM WHERE VOLTAGE > 550000000000000000 ", nativeQuery = true)
    ArrayList<String> findVoltageByCondition();

}
