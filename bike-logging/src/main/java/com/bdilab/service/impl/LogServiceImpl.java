package com.bdilab.service.impl;

import com.bdilab.entity.Log;
import com.bdilab.repository.LogRepository;
import com.bdilab.service.LogService;
import com.bdilab.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {

    private LogRepository logRepository;

    @Async
    @Override
    public void save(Log log) {
        String userName = SecurityUtil.getCurrentUserName();
        String ip = SecurityUtil.getIp();
        String browser = SecurityUtil.getBrowser();
        log.setOperator(userName);
        log.setRequestIp(ip);
        log.setBrowser(browser);
        logRepository.save(log);
    }

    @Override
    public Page<Log> queryByLogType(String logType,Pageable pageable) {
        return logRepository.findByLogType(logType,pageable);
    }


    public Page<Log> queryAll(Pageable pageable) {
        return logRepository.findAll(pageable);
    }

    @Override
    public Page<Log> queryUserLog(Pageable pageable) {
        String user = SecurityUtil.getCurrentUserName();
        return logRepository.findByOperator(user,pageable);
    }
}
