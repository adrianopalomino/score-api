# Análise de Perfil de Crédito API

Análise de Perfil de Crédito API foi desenvolvida para realizar a análise de crédito de clientes baseado em seu Score.

## Installation

A versão - Spring - utilizada neste contexto possui de modo nativo suporte para Docker. Portanto, basta executar as instruções descritas abaixo para a construção e execução da imagem, respectivamente:

```bash
./mvnw spring-boot:build-image

docker container run -p 8080:8080 docker.io/library/score-api:0.0.1-SNAPSHOT
```

Ou então, podemos flexibilizar a construção e execução - Docker - utilizando os mecanismos de build - Dockerfile - e execução - docker-compose, conforme descrito abaixo:

```bash
docker-compose up -d
```

## Instruções para testes

Após realizar a instalação

Selecione o link [Swagger](http://localhost:8080/swagger-ui.html)

Suas credenciais serão solicitadas, favor inserir os seguintes dados:

username: scoreapi

password: scoreapi

Selecione o item **analise-credito-resource** e verá 2 serviços pois a API foi desenvolvida com a característica descrita abaixo:

- Para evitar que o *client* da API aguarde o processamento das informações o contexto JMS - assíncrono - foi inserido na aplicação sendo que ao realizar a chamada para o *endpoint* **score-api/v1/analises** com o verbo HTTP **POST** o *client* receberá uma resposta do tipo **202 Accepted** com um *Header Location* informando a URL do recurso que irá disponibilizar a análise processada pela aplicação dentro de alguns instantes. Esta estratégia irá prover Confiabilidade, Resiliência (se ocorrer alguma falha no processamento da análise de crédito o registro será processado novamente), Performance e etc.

Segue um objeto para teste deste recurso:


{
  "cpf": "92388006007",
  "dependentes": 0,
  "idade": 20,
  "nome": "Pedro",
  "renda": 5000.00
}


- Após realizar a requisição **POST** será o momento de realizar a requisição **GET** uma vez que além da URL do recurso REST, responsável pela recuperação da análise de crédito, o serviço também disponibilizará o id (identificador para a recuperação da análise de crédito).


Basta copiar o identificador retornado no *Header Location* da requisição descrita acima e invocar o recurso **GET** para obter o resultado final da análise de crédito. Se preferir também é possível copiar o endereço completo retornado neste *Header* e realizar a requisição em uma nova aba do browser.

Este serviço poderá retornar um registro com 3 possíveis *status*, são eles:

- EM PROCESSAMENTO  (quando a análise ainda não foi processada)
- FINALIZADO        (quando a análise já foi processada com sucesso)
- SCORE INEXISTENTE (para casos em que o *score* advindo de uma integração externa é inexistente)  


## Riscos e mitigações

A aplicação possui mecanismo de mensagens JMS o que pode causar um anti padrão denominado **Hot Potato** uma vez que as mensagens podem entrar em loop entre a fila e o receiver. Para mitigar este problema a aplicação poderá utilizar uma quantidade específica de tentativas de processamento - 10x - e caso as *runtime exceptions* persistam uma outra forma de análise, padrão, poderá ser aplicada.

(Outros...) 
 