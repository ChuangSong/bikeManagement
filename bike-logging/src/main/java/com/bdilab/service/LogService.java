package com.bdilab.service;

import com.bdilab.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogService {

    void save(Log log);

    Page<Log> queryByLogType(String logType,Pageable pageable);

    Page<Log> queryAll(Pageable pageable);

    Page<Log> queryUserLog(Pageable pageable);
}
