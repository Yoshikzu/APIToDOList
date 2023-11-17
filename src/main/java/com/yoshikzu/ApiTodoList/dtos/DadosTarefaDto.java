package com.yoshikzu.ApiTodoList.dtos;

import com.yoshikzu.ApiTodoList.enums.Prioridade;
import com.yoshikzu.ApiTodoList.models.Tarefa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosTarefaDto(@NotBlank String nome,
                             String descricao,
                             LocalDateTime data,
                             boolean concluido,
                             Prioridade prioridade,
                             boolean enviarNotificacaoPorEmail,
                             @Email String email) {

    public DadosTarefaDto(Tarefa tarefa){
        this(tarefa.getNome(),tarefa.getDescricao(),tarefa.getDate(),tarefa.isConcluido(),tarefa.getPrioridade(), tarefa.isEnviarNotificacaoPorEmail(), tarefa.getEmail());
    }
}

