package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.LogAuditDto;
import com.schedulepiloto.core.entities.model.LogAuditEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface LogAuditService {

    ModelMapper modelMapper = new ModelMapper();

    static LogAuditEntity convertDTOToEntity(LogAuditDto dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        LogAuditEntity entity = modelMapper.map(dto, LogAuditEntity.class);
        return entity;
    }

    static LogAuditDto convertEntityToDTO(LogAuditEntity entity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (entity != null) {
            LogAuditDto dto = modelMapper.map(entity, LogAuditDto.class);
            return dto;
        } else {
            return null;
        }
    }

    void saveCommonLog(LogAuditDto logAuditDto);
}
