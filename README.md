# CEP API

Esta é uma aplicação Java Spring Boot que permite buscar informações de CEP em uma API externa mockada com Wiremock. A aplicação grava os logs das consultas em um banco de dados PostgreSQL e também salva esses logs no Amazon S3.

## Tecnologias Utilizadas

- Java 11
- Spring Boot
- PostgreSQL
- Wiremock
- Docker
- AWS S3

## Estrutura do Projeto

```plaintext
cep-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/
│   │   │   │   └── CepController.java
│   │   │   ├── service/
│   │   │   │   └── CepService.java
│   │   │   ├── repository/
│   │   │   │   └── CepLogRepository.java
│   │   │   └── model/
│   │   │       └── CepLog.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── docker-compose.yml
│   └── test/
│       └── java/            
│           ├── controller/
│           │   ├── CepControllerTest.java
│           │   └── CepControllerIntegrationTest.java
│           ├── service/
│           │   └── CepServiceTest.java
│           ├── repository/
│           │   └── CepLogRepositoryTest.java
│           └── model/
│               └── CepLogTest.java
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

Configuração e Execução
Pré-requisitos
- Docker
- Java 11+
- Maven

Passos para Configuração

1. Clone o repositório:
    git clone https://github.com/GabrielVendittiBarreira/cep-api.git
    cd cep-api

2. Suba os containers Docker:
    docker-compose up

3. Configure as credenciais AWS no application.properties:
    aws.accessKeyId=YOUR_ACCESS_KEY_ID
    aws.secretAccessKey=YOUR_SECRET_ACCESS_KEY
    aws.region=YOUR_AWS_REGION
    aws.s3.bucketName=YOUR_BUCKET_NAME

4.Execute a aplicação Spring Boot:
    ./mvnw spring-boot:run

Endpoints
- Buscar CEP: GET /buscar-cep?cep={cep}
    Exemplo: http://localhost:8080/buscar-cep?cep=12345678

Contribuição:
1. Fork o repositório
2. Crie uma branch para sua feature (git checkout -b feature/nova-feature)
3. Commit suas mudanças (git commit -am 'Adiciona nova feature')
4. Push para a branch (git push origin feature/nova-feature)
5. Abra um Pull Request


### Passos para Adicionar ao Git

1. **Navegue até o diretório do projeto**:
   ```sh
   cd /caminho/para/seu/projeto

2. Inicialize um repositório Git local:
    git init

3. Adicione todos os arquivos ao repositório:
    git add .

4.Faça um commit inicial:
    git commit -m "Initial commit"

5. Adicione o repositório remoto:
    git remote add origin https://github.com/GabrielVendittiBarreira/cep-api.git

Faça o push dos arquivos para o repositório remoto:
    git push -u origin master