package com.arc.udemo.service.impl;

import com.arc.udemo.domain.MailMessage;
import com.arc.udemo.service.EmailService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

   /* @Autowired
    private JavaMailSender mailSender;*/

    @Resource( name = "rabbitTemplate" )
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.exchange}")
    private String exchange;

    @Value("${mq.routekey}")
    private String routeKey;


    @Override
    public void sendEmail(MailMessage message) {
    //public void sendEmail(String content, String subject, String to) {

        /*MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("bolaji.salau@gmail.com");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }*/

        try {
            rabbitTemplate.convertAndSend(exchange, routeKey, message);
        }catch (Exception e){
            logger.error("EmailServiceImpl.sendEmail", ExceptionUtils.getMessage(e));
        }
    }
}
