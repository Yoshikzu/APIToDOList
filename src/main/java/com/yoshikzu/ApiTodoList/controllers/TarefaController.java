package com.yoshikzu.ApiTodoList.controllers;

import com.yoshikzu.ApiTodoList.dtos.DadosListaTarefa;
import com.yoshikzu.ApiTodoList.dtos.DadosTarefaDto;
import com.yoshikzu.ApiTodoList.models.Tarefa;
import com.yoshikzu.ApiTodoList.services.TarefaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    @Transactional
    public ResponseEntity adicionarTarefa(@RequestBody @Valid DadosTarefaDto dadosTarefa){
        var tarefa = new Tarefa();
        BeanUtils.copyProperties(dadosTarefa,tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.incluir(tarefa));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTarefa(@RequestBody @Valid DadosTarefaDto dadosTarefa){
        var tarefa = new Tarefa();
        BeanUtils.copyProperties(dadosTarefa,tarefa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.atualizar(tarefa));
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTarefa(@PathVariable Long id){
        var tarefa = tarefaService.getTarefa(id);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaTarefa>> listar(@PageableDefault(size=10) Pageable page){
        return ResponseEntity.ok(tarefaService.listarTodasTarefas(page).map(DadosListaTarefa::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTarefa(@PathVariable Long id){
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
