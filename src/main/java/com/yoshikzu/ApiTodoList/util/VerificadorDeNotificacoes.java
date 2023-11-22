package com.yoshikzu.ApiTodoList.util;

import com.yoshikzu.ApiTodoList.models.Tarefa;
import com.yoshikzu.ApiTodoList.services.EmailService;
import com.yoshikzu.ApiTodoList.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class VerificadorDeNotificacoes {
    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedDelay = HORA)
    public void verificaPorHora(){
        ArrayList<Tarefa> listaTarefas = tarefaService.listarTodasTarefasNaoConcluidasComMarcacaodeNotificar();

        for(Tarefa tarefa: listaTarefas){
            System.out.println(tarefa.getNome());
            if(tarefa.isEnviarNotificacaoPorEmail() && tarefa.getDate() != null){
                if(tarefa.getDate().getYear()== LocalDateTime.now().getYear() &&
                        tarefa.getDate().getMonth() == LocalDateTime.now().getMonth() &&
                        tarefa.getDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())  {
                    emailService.sendEmail(tarefa);
                }
            }
        }
    }
}
