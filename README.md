# <p align=center>DroneFeeder</p>

<div align="center" display="flex">
  <img
    src="https://user-images.githubusercontent.com/87498097/180002646-357f61a5-02a5-4cb5-a663-db80ed94e326.png"
    alt="Documentação de todas as rotas"
    height="300px"
    width="600px"
  />
  <img
    src="https://user-images.githubusercontent.com/87498097/180004088-1b1b5801-111a-43ec-8e9d-c9dc012101b9.png"
    alt="Exemplo de resposta da rota finalizar uma entrega PUT /delivery/id"
    height="300px"
    width="600px"
  />
</div>

#

Drone Feeder é uma aplicação REST de monitoramento de entregas feita por drones na qual um sistema Front-end pode exibir algumas informações
tais como latitude, longitude, data, horário da postagem, horário de retirada do pacote e vídeo com a gravação do momento da entrega.

Essas informações são armazenadas no banco de dados MySQL.

# Tecnologias

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Spring framework](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Apache maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

# Como usar

Ferramentas necessárias para usar a aplicação:

Você vai precisar do git instalado em sua máquina [Git](https://git-scm.com/downloads)

Do [Java 11 ou superior - open-jdk também funciona -](https://www.java.com/pt-BR/download/)

Apache [maven](https://maven.apache.org/download.cgi)

[Docker](https://docs.docker.com/desktop)

Docker compose [versão 1.29 ou superior](https://docs.docker.com/compose/install/)

Ao finalizar a instalação das ferramentas siga o passo a passo abaixo:


```
clone o repositório. 

$git clone git@github.com:leandromliveira/droneFeeder.git

mude para a pasta do projeto.

$cd droneFeeder

Instale as dependências caso hajam.

$mvn clean install

Suba o container

$docker-compose up
```

Nesse ponto a aplicação estará rodando no seu localhost na porta 8080.

Para mais detalhes sobre as rotas e como utilizá-las acesse: localhost:8080/swagger-ui/

Feito por [Leandro Oliveira](https://github.com/leandromliveira),
[Caio Magalhães](https://github.com/CaioMagalhaesPinheiro)
e [Valdeci de Moura](https://github.com/Valdeci97).
