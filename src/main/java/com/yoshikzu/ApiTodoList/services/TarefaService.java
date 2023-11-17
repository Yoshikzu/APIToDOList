package com.yoshikzu.ApiTodoList.services;

import com.yoshikzu.ApiTodoList.models.Tarefa;
import com.yoshikzu.ApiTodoList.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa incluir(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Tarefa tarefa){
        var tarefaParaAtualizar = getTarefa(tarefa.getId());

        if(tarefa.getNome() != null){
            tarefaParaAtualizar.setNome(tarefa.getNome());
        }

        if(tarefa.getDescricao() != null){
            tarefaParaAtualizar.setDescricao(tarefa.getDescricao());
        }

        if(tarefa.getEmail() != null){
            tarefaParaAtualizar.setEmail(tarefa.getEmail());
        }

        if(tarefa.getPrioridade() != null){
            tarefaParaAtualizar.setPrioridade(tarefa.getPrioridade());
        }

        tarefaParaAtualizar.setEnviarNotificacaoPorEmail(tarefa.isEnviarNotificacaoPorEmail());
        tarefaParaAtualizar.setDate(tarefa.getDate());
        tarefaParaAtualizar.setConcluido(tarefa.isConcluido());
        tarefaRepository.save(tarefaParaAtualizar);
        return tarefaParaAtualizar;
    }

    public Page<Tarefa> listarTodasTarefas(Pageable page){
        return tarefaRepository.findAll(page);
    }

    public void excluir(Long id){
        var tarefaEncontrada = getTarefa(id);
        tarefaRepository.delete(tarefaEncontrada);
    }

    //Método que retornará uma tarefa de acordo com o id passado como parametro
    //Caso não encontre será lançada uma exceção com a descrição que a tarefa não encontrada no sistema
    public Tarefa getTarefa(Long id){
        var tarefaEncontrada = tarefaRepository.findById(id).orElse(null);
        if(tarefaEncontrada == null){
            throw new RuntimeException("Tarefa (id: " +  id + ") não encontrada no sistema!");
        }
        return tarefaEncontrada;
    }

}
