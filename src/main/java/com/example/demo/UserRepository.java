package com.example.demo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String SELECT_USER_BY_USERID = "select " +
            "distinct new map( u.userId as userId, u.userName as userName, d.deptName as departmentName) " +
            "from User u " +
            "join u.department d " +
            "where u.userId = :userId";

    @Query(value = SELECT_USER_BY_USERID)
    Map <String, Object> findUserbyUserId(@Param("userId") Long userId);

    Optional<User> findById(Long userId);
}