# ApiToDoList

API ToDoList criado utilizando Spring Boot versão 3.2.0

-----------------------------------------

Tecnologias utilizadas:
- Spring Web
- Spring Data JPA
- Spring Dev Tools
- PostgreSQL Driver
- Validation

-----------------------------------------

## 📚 Documentação

Criada uma API que irá armazenar a tabela de listas para serem feitas em PostgreSQL, onde irá conter as informações de nome, descrição, data, tarefa foi concluida, prioridade e uma opção se deseja notificar por email e o email que deverá receber o lembrete.

Foi implementado os endpoints da API para inclusão, alteração, exclusão e a visualização de todas as tarefas ou uma especifica.

Criado também a funcionalidade que será executada a cada uma hora (através da anotação @EnableScheduling), onde o sistema irá verificar se existe uma tarefa para ser feita no mesmo dia e uma hora antes, caso encontre e tenha a marcação de enviar a notificação por email será enviada um email com nome da tarefa e horário como lembrete
