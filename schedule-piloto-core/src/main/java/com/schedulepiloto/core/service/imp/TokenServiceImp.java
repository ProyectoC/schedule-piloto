package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.dto.model.TokenDto;
import com.schedulepiloto.core.entities.model.TokenEntity;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.repository.TokenRepository;
import com.schedulepiloto.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class TokenServiceImp implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    @Transactional
    public TokenDto getByIdNull(Long id) {
        Optional<TokenEntity> entity = this.tokenRepository.findById(id);
        return entity.map(TokenService::convertEntityToDTO).orElse(null);
    }

    @Override
    @Transactional
    public TokenDto getByIdAndActivateUserThrow(Long id) throws SchedulePilotoException {
        Optional<TokenEntity> entity = this.tokenRepository.findByIdAndActivateUser(id);
        return entity.map(TokenService::convertEntityToDTO).orElseThrow(() -> new SchedulePilotoException("Token Activate User Not Found"));
    }

    @Override
    @Transactional
    public TokenDto save(TokenDto tokenDto) {
        TokenEntity tokenEntity = TokenService.convertDTOToEntity(tokenDto);
        return TokenService.convertEntityToDTO(this.tokenRepository.save(tokenEntity));
    }

    @Override
    @Transactional
    public TokenDto update(TokenDto tokenDto) {
        TokenEntity tokenEntity = TokenService.convertDTOToEntity(tokenDto);
        return TokenService.convertEntityToDTO(this.tokenRepository.save(tokenEntity));
    }
}
