package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.ParameterDto;
import com.schedulepiloto.core.entities.model.ParameterEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParameterService {

    ModelMapper modelMapper = new ModelMapper();

    static ParameterEntity convertDTOToEntity(ParameterDto dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ParameterEntity entity = modelMapper.map(dto, ParameterEntity.class);
        return entity;
    }

    static ParameterDto convertEntityToDTO(ParameterEntity entity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (entity != null) {
            ParameterDto dto = modelMapper.map(entity, ParameterDto.class);
            return dto;
        } else {
            return null;
        }
    }

    ParameterDto getByNameNull(String name);

    List<ParameterDto> getAll();
}
