package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.dto.model.LogAuditDto;
import com.schedulepiloto.core.repository.LogAuditRepository;
import com.schedulepiloto.core.service.LogAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class LogAuditServiceImp implements LogAuditService {

    @Autowired
    private LogAuditRepository logAuditRepository;

    @Override
    @Async
    @Transactional
    public void saveCommonLog(LogAuditDto logAuditDto) {
        logAuditDto.setIsActive(true);
        this.logAuditRepository.save(LogAuditService.convertDTOToEntity(logAuditDto));
    }
}
