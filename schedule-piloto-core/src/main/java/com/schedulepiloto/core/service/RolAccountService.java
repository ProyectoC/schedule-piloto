package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.RolAccountDto;
import com.schedulepiloto.core.entities.model.RolAccountEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolAccountService {
    ModelMapper modelMapper = new ModelMapper();

    static RolAccountEntity convertDTOToEntity(RolAccountDto dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        RolAccountEntity entity = modelMapper.map(dto, RolAccountEntity.class);
        return entity;
    }

    static RolAccountDto convertEntityToDTO(RolAccountEntity entity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (entity != null) {
            RolAccountDto dto = modelMapper.map(entity, RolAccountDto.class);
            return dto;
        } else {
            return null;
        }
    }

    List<RolAccountDto> getAll();
}
