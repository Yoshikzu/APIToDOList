# ApiToDoList
API ToDoList criando utilizando Spring boot

Tecnologias utilizadas:
- Spring Web
- Spring Data JPA
- Spring Dev Tools
- PostgreSQL Driver
- Validation


Etapa 1:
Concluido => Criar uma tabela que será armazenada em PostgreSQL que irá armazenar as tarefas cadastradas, onde deverá ter informações como nome, descrição, data, realizado, prioridade, opção se deseja notificar por email e caso marque deverá informar o email.
Concluido => Criar endpoints para cadastrar, alterar, excluir e visualizar as tarefas.


Etapa 2:
Concluido => Implementar a funcionalidade que deverá notificar por email o usuário referente ao agendamento da tarefa

Obs:
Foi implementado uma funcionalidade que será executada a cada uma hora (através da anotação @EnableScheduling), onde o sistema irá verificar se existe uma tarefa para ser feita no mesmo dia e uma hora antes, caso encontre e tenha a marcação de enviar a notificação por email será enviada um email com nome da tarefa e horário como lembrete