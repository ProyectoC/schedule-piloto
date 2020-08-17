package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.dto.model.TokenTypeDto;
import com.schedulepiloto.core.repository.TokenTypeRepository;
import com.schedulepiloto.core.service.TokenTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class TokenTypeServiceImp implements TokenTypeService {

    @Autowired
    private TokenTypeRepository repository;

    @Override
    @Transactional
    public List<TokenTypeDto> getAll() {
        List<TokenTypeDto> list = new ArrayList<>();
        repository.findAll().forEach(e -> list.add(TokenTypeService.convertEntityToDTO(e)));
        return list;
    }
}
