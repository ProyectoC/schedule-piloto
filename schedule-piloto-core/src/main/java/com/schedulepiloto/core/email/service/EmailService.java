package com.schedulepiloto.core.email.service;

import com.schedulepiloto.core.email.model.EmailDto;
import com.schedulepiloto.core.email.model.EmailProperties;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(EmailProperties emailProperties, EmailDto emailDto) throws SchedulePilotoException;
}
