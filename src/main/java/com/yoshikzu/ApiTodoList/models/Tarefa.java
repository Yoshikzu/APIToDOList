package com.yoshikzu.ApiTodoList.models;

import com.yoshikzu.ApiTodoList.enums.Prioridade;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="TB_TAREFAS")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private LocalDateTime date;
    private boolean concluido;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private boolean enviarNotificacaoPorEmail;
    private String email;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isEnviarNotificacaoPorEmail() {
        return enviarNotificacaoPorEmail;
    }

    public void setEnviarNotificacaoPorEmail(boolean enviarNotificacaoPorEmail) {
        this.enviarNotificacaoPorEmail = enviarNotificacaoPorEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
