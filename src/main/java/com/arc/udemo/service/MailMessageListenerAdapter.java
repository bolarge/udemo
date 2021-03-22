package com.arc.udemo.service;

import com.arc.udemo.domain.MailMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Component("mailMessageListenerAdapter")
public class MailMessageListenerAdapter extends MessageListenerAdapter {

	@Resource
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String mailUsername;

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		System.out.println(message.getMessageProperties().getConsumerQueue());
		try {
			String messageBody = new String(message.getBody());
            ObjectMapper objectMapper = new ObjectMapper();

            MailMessage mailMessageModel = objectMapper.readValue(messageBody, MailMessage.class);
			String to =  mailMessageModel.getTo();
			String subject = mailMessageModel.getSubject();
			String text = mailMessageModel.getText();
			sendHtmlMail(to, subject, text);
			//
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void sendHtmlMail(String to, String subject, String text) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(mailUsername);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(text, true);
		//
		mailSender.send(mimeMessage);
	}
}
