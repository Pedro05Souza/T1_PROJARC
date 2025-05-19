# Como Iniciar o Projeto

Este projeto utiliza Docker para gerenciar o banco de dados e o Maven para rodar a aplicação Spring Boot.

---

## Pré-requisitos

- **Docker** instalado e em execução  
  Para instalar o Docker, entre em um destes links:

  - [**Windows**](https://docs.docker.com/desktop/install/windows-install/)
  - [**Linux**](https://docs.docker.com/desktop/install/linux-install/)
  - [**macOS**](https://docs.docker.com/desktop/install/mac-install/)

- **Java** instalado
- **Maven** instalado

---

## Para rodar o Projeto primeiro inicialize o Banco de Dados com Docker e depois inicie o programa:

Navegue até a raiz do projeto e execute:

```bash
docker compose up
mvn spring-boot:run
