# Submissão de API de estacionamento para FCamara
_Desenvolvedor: Rafael Cordeiro_
___

## Tecnologias utilizadas:

- Java 21;
- Maven 3.9.9;
- Spring Boot 3.3.3;
- Spring Data JPA;
- JUnit 5;
- H2 Database.

## Endpoints:

### CRUD Estabelecimento

**POST** localhost:9999/api/estabelecimento

```json
// exemplo de request body
{
    "nome": "Estapar",
    "cnpj": "12.345.678/0001-90",
    "endereco": "Rua cinco - São Paulo",
    "telefone": "(11) 98765-4321",
    "qtdeVagasMotos": 25,
    "qtdeVagasCarros": 15
}
```

**GET** localhost:9999/api/estabelecimento

**PUT** localhost:9999/api/estabelecimento/{id}

```json
// exemplo de request body
{
    "nome": "Mogi Parking",
    "cnpj": "12.345.678/0001-90",
    "endereco": "Rua braz cubas, 121 - Mogi das Cruzes - SP",
    "telefone": "(11) 99999-9999",
    "qtdeVagasMotos": 25,
    "qtdeVagasCarros": 15
}
```

**DELETE** localhost:9999/api/estabelecimento/{id}

## Controle de Entrada e Saída

### Cadastro de E/S
**POST** localhost:9999/api/controleentradasaida

```json
// exemplo de request body
{
    "nome": "Mogi Parking",
    "cnpj": "12.345.678/0001-90",
    "endereco": "Rua braz cubas, 121 - Mogi das Cruzes - SP",
    "telefone": "(11) 99999-9999",
    "qtdeVagasMotos": 25,
    "qtdeVagasCarros": 15
}
```

### Consulta de relatório de Estabelecimentos
**POST** localhost:9999/api/relatorioestabelecimento/{id}

```json
// exemplo de resposta
{
  "success": true,
  "messages": null,
  "entity": {
    "estabelecimento": {
      "id": 1,
      "nome": "Estapar",
      "cnpj": "12.345.678/0001-90",
      "endereco": "Rua cinco, 454 - São Paulo - SP",
      "telefone": "(11) 98765-4321",
      "qtdeVagasMotos": 20,
      "qtdeVagasCarros": 15
    },
    "qtdeEntradasHora": 2.6666666666666665,
    "qtdeSaidasHora": 1.6666666666666667,
    "qtdeEntradasTotal": 8.0,
    "qtdeSaidasTotal": 5.0,
    "vagasCarroOcupadas": 3,
    "vagasMotoOcupadas": 0
  },
  "entities": null
}
```

## Configuração

Toda a configuração para testar a API, desde que haja IDE IntelliJ com Java 21 configurado, não é necessária. Basta fazer a importação do projeto e executar a classe `BackendTestJavaFcamaraApplication.java`.

Caso não possua a IDE, se faz necessário realizar os builds com comandos maven. Para isso, é necessário possuir JDK Java 21 e maven de versão compatível (indico 3.9.9) Segue abaixo os comandos:

```shell
# Para fazer o build
mvn clean install -U -DskipTests
# Para executar o servidor
mvn spring-boot:run
```
Todos os comandos devem ser realizados no mesmo diretório onde há o arquivo `pom.xml`.