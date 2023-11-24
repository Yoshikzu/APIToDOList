package com.yoshikzu.ApiTodoList.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    @Value(value="${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(String emailEnvio, String assunto, String conteudoEmail){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailEnvio);
            message.setSubject(assunto);
            message.setText(conteudoEmail);
            emailSender.send(message);
        }catch (MailException e){
            System.out.println(e.getMessage());
        }
    }

}
