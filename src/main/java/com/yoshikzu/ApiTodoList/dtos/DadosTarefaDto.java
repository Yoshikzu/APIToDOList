package com.yoshikzu.ApiTodoList.dtos;

import com.yoshikzu.ApiTodoList.enums.Prioridade;
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
}

