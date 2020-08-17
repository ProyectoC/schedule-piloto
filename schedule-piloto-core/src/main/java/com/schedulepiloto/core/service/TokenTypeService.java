package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.TokenTypeDto;
import com.schedulepiloto.core.entities.model.TokenTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TokenTypeService {

    ModelMapper modelMapper = new ModelMapper();

    static TokenTypeEntity convertDTOToEntity(TokenTypeDto dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TokenTypeEntity entity = modelMapper.map(dto, TokenTypeEntity.class);
        return entity;
    }

    static TokenTypeDto convertEntityToDTO(TokenTypeEntity entity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (entity != null) {
            TokenTypeDto dto = modelMapper.map(entity, TokenTypeDto.class);
            return dto;
        } else {
            return null;
        }
    }

    List<TokenTypeDto> getAll();
}
