# Análise de Perfil de Crédito API

Essa API foi desenvolvida para simular análises de crédito de pessoas baseado-se em seus dados.

## Tecnologias utilizadas

- Java (11)
- Spring boot (2.3.4)
- Maven 3.x
- Docker 
- Docker Compose

## Instalação

A versão - Spring - utilizada neste projeto possui, de modo nativo, suporte para Docker. Por essa razão, basta executar as instruções descritas abaixo para a construção e execução da imagem, respectivamente:

```bash
./mvnw spring-boot:build-image

docker container run -p 8080:8080 docker.io/library/score-api:0.0.1-SNAPSHOT
```

Ou então, podemos flexibilizar a construção e execução - Docker - utilizando os mecanismos de *build* - Dockerfile - e execução - docker-compose, executando a instrução descrita abaixo:

```bash
docker-compose up -d
```

## Decisões impotantes

Para evitar que o *client* da API aguarde o processamento das informações o contexto JMS - assíncrono - foi inserido na aplicação e, portanto, a solução foi "micro" dividida em etapas.

- Na primeira etapa o *client* da API irá requisitar o recurso **score-api/v1/analises** com o verbo HTTP **POST** e, caso tudo ocorra com sucesso, receberá uma resposta do tipo **202 Accepted**. Essa resposta possuirá um *Header Location* informando a URL do recurso que, dentro de alguns instantes, retornará a análise de credito já processada. 

- Na segunda etapa o *client* da API poderá recuperar a análise de crédito. Essa operação poderá ser executada invocando o recurso **/score-api/v1/analises/{id}** com o verbo HTTP **GET**. A identificação desse recurso será descrita no *Header Location* recuperado na etapa anterior.


Tudo foi desenvolvido da forma mais simples possível.

## Instruções para testes

Após realizar a instalação e execução da API

**1** - Selecione o link [Swagger](http://localhost:8080/swagger-ui.html)

**2** - Preencha os campos com as credenciais descritas abaixo:

**username**: scoreapi

**password**: scoreapi

**3** - Selecione o item **analise-credito-resource** 

**3.1** - Realize a requisição para o recurso **POST** com um dos objetos descritos abaixo:


- Cenários de sucesso:

{
  "cpf": "92388006007",
  "dependentes": 0,
  "idade": 20,
  "nome": "Pedro",
  "renda": 5000.00
}

{
  "cpf": "70060842024",
  "dependentes": 0,
  "idade": 18,
  "nome": "João",
  "renda": 1045.00
}

{
  "cpf": "10406392080",
  "dependentes": 1,
  "idade": 25,
  "nome": "José",
  "renda": 3000.00
}

{
  "cpf": "28340240064",
  "dependentes": 2,
  "idade": 53,
  "nome": "Mateus",
  "renda": 10000.00
}

{
  "cpf": "74384183089",
  "dependentes": 0,
  "idade": 65,
  "nome": "Maria",
  "renda": 2000.00
}

{
  "cpf": "76898015010",
  "dependentes": 1,
  "idade": 32,
  "nome": "Fernanda",
  "renda": 6000.00
}

- Cenário com falha no processamento:

{
  "cpf": "10146710002",
  "dependentes": 3,
  "idade": 27,
  "nome": "Joaquim",
  "renda": 3000.00
}



**3.2** - Realize a requisição para o recurso **GET** utilizando o identificador retornado no *Header Location* da requisição **POST**, ou cole a URL disponibilizada em outra aba.

Este recurso poderá retornar uma análise de crédito com 3 possíveis status, são eles:

- EM PROCESSAMENTO (quando a análise ainda não foi processada)

- FINALIZADO (quando a análise já foi processada com sucesso)

- SCORE INEXISTENTE (para casos em que o score advindo de uma integração externa é inexistente)

## Riscos e mitigações

A aplicação possui mecanismo de mensagens JMS o que pode causar um anti-padrão denominado **Hot Potato**. Para mitigar este problema a aplicação poderá utilizar uma quantidade específica de tentativas de processamento - 10x - e caso as *runtime exceptions* persistam uma outra forma de análise, padrão, poderá ser aplicada.

## Itens abordados na solução

● Design da solução
● Síncrono/Assíncrono
● Desempenho
● Legibilidade
● Camadas/Estrutura
● Algoritmo
● Segurança 