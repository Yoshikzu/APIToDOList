package com.yoshikzu.ApiTodoList.services;

import com.yoshikzu.ApiTodoList.models.Tarefa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailService {
    final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    @Value(value="${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(Tarefa tarefa){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(tarefa.getEmail());
            message.setSubject("ToDOList - Lembrete " + tarefa.getNome());
            message.setText(tarefa.getDescricao() + tarefa.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            emailSender.send(message);
        }catch (MailException e){
            System.out.println(e.getMessage());
        }
    }

}
