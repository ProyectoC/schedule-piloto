package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.UserAccountDto;
import com.schedulepiloto.core.entities.model.UserAccountEntity;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.request.UserAccountCreateRequest;
import com.schedulepiloto.core.util.dto.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountService extends UserDetailsService {

    ModelMapper modelMapper = new ModelMapper();

    static UserAccountDto convertRequestToDTO(UserAccountCreateRequest dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserAccountDto entity = modelMapper.map(dto, UserAccountDto.class);
        return entity;
    }

    static UserAccountEntity convertDTOToEntity(UserAccountDto dto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserAccountEntity entity = modelMapper.map(dto, UserAccountEntity.class);
        return entity;
    }

    static UserAccountDto convertEntityToDTO(UserAccountEntity entity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (entity != null) {
            UserAccountDto dto = modelMapper.map(entity, UserAccountDto.class);
            return dto;
        } else {
            return null;
        }
    }

    UserAccountDto getByIdNull(Long id);

    UserAccountDto getByIdThrow(Long id) throws SchedulePilotoException;

    UserAccountDto getByUsername(String username);

    UserAccountDto getByUsernameThrow(String username) throws SchedulePilotoException;

    UserAccountDto getByIdentification(String identification);

    UserAccountDto getByIdentificationCode(String identificationCode);

    UserAccountDto getByEmailOrEmailBackup(String email);

    UserDetails loadUserById(Long id);

    Validator validationUserBeforeSave(UserAccountDto userAccountDto);

    UserAccountDto save(UserAccountDto userAccountDto);

    UserAccountDto update(UserAccountDto userAccountDto);
}
