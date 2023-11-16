package com.yoshikzu.ApiTodoList.repositories;

import com.yoshikzu.ApiTodoList.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long> {

}
