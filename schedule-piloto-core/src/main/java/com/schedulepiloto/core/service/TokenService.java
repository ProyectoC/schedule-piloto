package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.TokenDto;
import com.schedulepiloto.core.entities.model.TokenEntity;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    ModelMapper modelMapper = new ModelMapper();

    static TokenEntity convertDTOToEntity(TokenDto dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TokenEntity entity = modelMapper.map(dto, TokenEntity.class);
        return entity;
    }

    static TokenDto convertEntityToDTO(TokenEntity entity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (entity != null) {
            TokenDto dto = modelMapper.map(entity, TokenDto.class);
            return dto;
        } else {
            return null;
        }
    }

    TokenDto getByIdNull(Long id);

    TokenDto getByIdAndActivateUserThrow(Long id) throws SchedulePilotoException;

    TokenDto save(TokenDto tokenDto);

    TokenDto update(TokenDto tokenDto);
}
