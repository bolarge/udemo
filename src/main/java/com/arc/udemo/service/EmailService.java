package com.arc.udemo.service;

import com.arc.udemo.domain.MailMessage;

public interface EmailService {

    void sendEmail(MailMessage mailMessage) throws Exception;
    //void sendUserApplicationRequestConfirmation() throws Exception;

    //void sendEmail(String content, String subject, String to) throws Exception;

}
