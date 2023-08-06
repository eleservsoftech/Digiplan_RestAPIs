package com.digiplan.repositories;

import com.digiplan.entities.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface DemoRepository extends JpaRepository<Demo, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE demo set name=:name, email=:email, number=:number where id = :id", nativeQuery = true)
    int updateUser(@Param("name") String name, @Param("email") String email, @Param("number") String numer, @Param("id") int id);

}
