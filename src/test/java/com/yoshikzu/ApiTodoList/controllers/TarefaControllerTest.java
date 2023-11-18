package com.yoshikzu.ApiTodoList.controllers;

import com.yoshikzu.ApiTodoList.dtos.DadosTarefaDto;
import com.yoshikzu.ApiTodoList.enums.Prioridade;
import com.yoshikzu.ApiTodoList.models.Tarefa;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TarefaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosTarefaDto> dadosTarefaDtoJson;

    @Autowired
    private JacksonTester<Tarefa> dadosDetalhamentoTarefaJson;

    @Test
    @DisplayName("Deveria cadastrar tarefa e devolver codigo http 201")
    void cenario_a_cadastrar_tarefa() throws Exception{
        var dadosCadastro = new DadosTarefaDto("Tarefa","Descricao",null,false,Prioridade.ALTA,false,null);

        var response = mvc.perform(post("/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosTarefaDtoJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var tarefa = new Tarefa(1L,"Tarefa","Descricao",null,false,Prioridade.ALTA,false,null);
        var jsonEsperado = dadosDetalhamentoTarefaJson.write(tarefa).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo http 400 pois tentou gravar com informacoes invalidas")
    void cenario_b_cadastrar_tarefa_invalida() throws Exception {
        var response = mvc
                .perform(post("/tarefas"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 400, pois tentou gravar tarefa sem nome")
    void cenario_c_cadastrar_tarefa_sem_nome() throws Exception{
        var dadosCadastro = new DadosTarefaDto(null,"Descricao",null,false,Prioridade.ALTA,false,null);

        var response = mvc
                .perform(post("/tarefas"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Deveria devolver código http 200 e retornar a tarefa cadastrada")
    void cenario_d_visualizar_tarefa() throws Exception{
        //var dadosCadastro = new DadosTarefaDto("Tarefa","Descricao",null,false,Prioridade.ALTA,false,null);

        /*var response = mvc.perform(post("/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosTarefaDtoJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();
*/
        var tarefa = new Tarefa(1L,"Tarefa","Descricao",null,false,Prioridade.ALTA,false,null);

        var jsonEsperado = dadosDetalhamentoTarefaJson.write(tarefa).getJson();

        var response = mvc
                .perform(get("/tarefas/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria retornar código 204 e excluir a tarefa")
    void cenario_e_excluir_tarefa() throws Exception{
        var dadosCadastro = new DadosTarefaDto("Tarefa","Descricao",null,false,Prioridade.ALTA,false,null);

        var response = mvc.perform(post("/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosTarefaDtoJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        response = mvc.perform(delete("/tarefas/1"))
                          .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

}
