package com.bdilab.repository;

import com.bdilab.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log,Long> {
    Page<Log> findByLogType(String logType, Pageable pageable);

    Page<Log> findByOperator(String user, Pageable pageable);
}
