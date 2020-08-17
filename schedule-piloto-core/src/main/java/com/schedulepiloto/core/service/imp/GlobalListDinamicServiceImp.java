package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.dto.model.ParameterDto;
import com.schedulepiloto.core.dto.model.RolAccountDto;
import com.schedulepiloto.core.dto.model.TokenTypeDto;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.service.GlobalListDinamicService;
import com.schedulepiloto.core.util.dto.GlobalListDinamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class GlobalListDinamicServiceImp implements GlobalListDinamicService {

    @Autowired
    private GlobalListDinamic<TokenTypeDto> globalTokenTypesList;

    @Autowired
    private GlobalListDinamic<ParameterDto> globalParameterList;

    @Autowired
    private GlobalListDinamic<RolAccountDto> globalRolAccountList;

    @Override
    @Transactional
    public TokenTypeDto getTokenTypeByNameThrow(String tokenTypeName) throws SchedulePilotoException {
        Optional<TokenTypeDto> optionalTokenTypeDto = this.globalTokenTypesList.getItems().stream().filter(parameter ->
                parameter.getName().equals(tokenTypeName)).findFirst();
        if (optionalTokenTypeDto.isPresent()) {
            return optionalTokenTypeDto.get();
        }
        throw new SchedulePilotoException("Token Type not found.");
    }

    @Override
    @Transactional
    public RolAccountDto getRolAccountByIdThrow(Long id) throws SchedulePilotoException {
        Optional<RolAccountDto> optional = this.globalRolAccountList.getItems().stream().filter(parameter ->
                parameter.getId().equals(id)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new SchedulePilotoException("Rol Account not found.");
    }

    @Override
    @Transactional
    public String getParameterValueNull(String keyParameter) {
        Optional<ParameterDto> parameterSecurityDto = this.globalParameterList.getItems().stream().filter(parameter ->
                parameter.getName().equals(keyParameter)).findFirst();
        return parameterSecurityDto.map(ParameterDto::getValue).orElse(null);
    }

    @Override
    @Transactional
    public String getParameterValueEmpty(String keyParameter) {
        Optional<ParameterDto> parameterSecurityDto = this.globalParameterList.getItems().stream().filter(parameter ->
                parameter.getName().equals(keyParameter)).findFirst();
        return parameterSecurityDto.map(ParameterDto::getValue).orElse("");
    }

    @Override
    @Transactional
    public String getParameterValueThrow(String keyParameter) throws SchedulePilotoException {
        Optional<ParameterDto> parameterSecurityDto = this.globalParameterList.getItems().stream().filter(parameter ->
                parameter.getName().equals(keyParameter)).findFirst();
        return parameterSecurityDto.map(ParameterDto::getValue).orElseThrow(() ->
                new SchedulePilotoException("Parameter with key: " + keyParameter + " not found."));
    }
}
