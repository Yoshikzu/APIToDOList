package com.yoshikzu.ApiTodoList.repositories;

import com.yoshikzu.ApiTodoList.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long> {

    @Query(value ="SELECT t.id, t.nome, t.descricao, t.date, t.concluido, t.prioridade, t.enviar_Notificacao_Por_Email, t.email FROM TB_TAREFAS t WHERE t.concluido = false AND t.enviar_Notificacao_Por_Email = true;",nativeQuery = true)
    List<Tarefa> listarTarefasNaoConcluidasComMarcacaodeNotificar();

}

