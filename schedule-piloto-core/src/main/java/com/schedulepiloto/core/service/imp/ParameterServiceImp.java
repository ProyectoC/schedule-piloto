package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.dto.model.ParameterDto;
import com.schedulepiloto.core.entities.model.ParameterEntity;
import com.schedulepiloto.core.repository.ParameterRepository;
import com.schedulepiloto.core.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ParameterServiceImp implements ParameterService {

    @Autowired
    private ParameterRepository repository;

    @Override
    @Transactional
    public ParameterDto getByNameNull(String name) {
        Optional<ParameterEntity> entity = repository.findByName(name);
        return entity.map(ParameterService::convertEntityToDTO).orElse(null);
    }

    @Override
    @Transactional
    public List<ParameterDto> getAll() {
        List<ParameterDto> list = new ArrayList<>();
        repository.findAll().forEach(e -> list.add(ParameterService.convertEntityToDTO(e)));
        return list;
    }
}
