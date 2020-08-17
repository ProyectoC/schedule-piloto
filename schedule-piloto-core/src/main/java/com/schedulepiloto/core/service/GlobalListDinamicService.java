package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.RolAccountDto;
import com.schedulepiloto.core.dto.model.TokenTypeDto;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import org.springframework.stereotype.Service;

@Service
public interface GlobalListDinamicService {

    TokenTypeDto getTokenTypeByNameThrow(String tokenTypeName) throws SchedulePilotoException;

    RolAccountDto getRolAccountByIdThrow(Long id) throws SchedulePilotoException;

    String getParameterValueNull(String keyParameter);

    String getParameterValueEmpty(String keyParameter);

    String getParameterValueThrow(String keyParameter) throws SchedulePilotoException;
}
