package com.yoshikzu.ApiTodoList.dtos;

import com.yoshikzu.ApiTodoList.enums.Prioridade;
import com.yoshikzu.ApiTodoList.models.Tarefa;

import java.time.LocalDateTime;

public record DadosListaTarefa(Long id,
                               String nome,
                               String descricao,
                               LocalDateTime date,
                               boolean concluido,
                               Prioridade prioridade,
                               boolean enviarNotificacaoPorEmail,
                               String email) {

    public DadosListaTarefa(Tarefa tarefa){
        this(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), tarefa.getDate(), tarefa.isConcluido(), tarefa.getPrioridade(),tarefa.isEnviarNotificacaoPorEmail(), tarefa.getEmail());
    }
}
