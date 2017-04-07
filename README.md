
# atech desafio

Dependencia postgres e rabbitmq docker-compose

Comando para subir e baixar images com docker-compose
./docker/docker-compose up|start|stop -d

Aplicação
Desafio Atech - A aplicação utiliza spring boot (spring data, spring amqp, spring securit ), mapstruts, hibernate, liquibase, jetty

* Deleção Logica para os registros
* Criação de Eventos no gerenciamento de fila
* Versionamente banco de dados com liquibase
* MapStruts coversão de Entidade para DTO
* CRUD Completo para Mercadoria e Nota Fiscal
* Emitentes registrados no script liquibase
* Utilização @JMSListener
* Angular usando Materialize
* Angular Notificações para visulização do Events das filas


#LOGIN 

Usando AuthenticationManagerBuilder.inMemoryAuthentication

Usuario : admin
Senha: admin

#Start
mvn spring-boot:run 
localhost:8080/atech
sobe backend

Front-end
npm i
bower install ( obs : escolher versão angular 1.6.1 )
Executar gulp -> gulp serve

#BackLog
Teste Integrados
Implementação de Websocket
