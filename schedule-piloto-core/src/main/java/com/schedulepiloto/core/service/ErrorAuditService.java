package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.ErrorAuditDto;
import com.schedulepiloto.core.entities.model.ErrorAuditEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface ErrorAuditService {

    ModelMapper modelMapper = new ModelMapper();

    static ErrorAuditEntity convertDTOToEntity(ErrorAuditDto dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ErrorAuditEntity entity = modelMapper.map(dto, ErrorAuditEntity.class);
        return entity;
    }

    static ErrorAuditDto convertEntityToDTO(ErrorAuditEntity entity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (entity != null) {
            ErrorAuditDto dto = modelMapper.map(entity, ErrorAuditDto.class);
            return dto;
        } else {
            return null;
        }
    }

    void saveCommonError(ErrorAuditDto errorAuditDto);
}
