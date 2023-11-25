package com.yoshikzu.ApiTodoList.exceptions;

public class TarefaNaoEncontrada extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TarefaNaoEncontrada(Long id){
        super("Tarefa (id: " +  id + ") não encontrada no sistema!");
    }
}
