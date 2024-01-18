# API SV.LIBRARY

<p align="center">
    <a href="#computer-sobre-o-projeto"> Sobre </a> •
    <a href="#gear-funcionalidades"> Funcionalidades </a> •
    <a href="#wrench-tecnologias"> Tecnologias </a> •
    <a href="#book-como-executar"> Como executar </a> •
    <a href="#mag-observações"> Observações </a> •
    <a href="#information_desk_person-autor"> Autor </a> •
    <a href="#licença"> Licença </a>
</p>

## :computer: Sobre o Projeto

API desenvolvida com inspiração na formação "Java e Spring Boot" da Alura. <br>
O objetivo desta API é fornecer métodos que acessem e manipulem os dados de uma biblioteca física. Não tem como propósito ser o mais fiel possível à realidade; por isso, não há incluídos dados como ISBN e outros. No futuro, isso pode vir a mudar. <br>
Optou-se por utilizar rotas e dados em português por questões de maior acessibilidade; no entanto, todos os membros de classes e restante do código estão em inglês.

## :gear: Funcionalidades

- [x] Livros: Rota `/livros`

  - [x] Criação: Verbo POST
    - Recebe um `CreateBookDTO`, chama a classe `BookService` para criar a entidade e retorna para a rota `/livros/{id}` mostrando os detalhes do novo livro.
  - [x] Leitura: Verbo GET
    - [x] Lista de livros;
    - [x] 1 único livro.
  - [x] Atualização: Verbo PUT
    - Recebe um `UpdateBookDTO`, salva a entidade e retorna seus dados.
  - [x] Reativação: Verbo PUT
    - Recebe um `id` de um livro que foi excluído e o restaura.
  - [x] Deleção: Verbo DELETE
    - Recebe um `id` de um livro, verifica-o no banco e realiza a exclusão lógica.

- [ ] Usuários

  a) Autenticação

  - [x] Cadastro: Rota `/auth/cadastro`, verbo POST
    - Recebe um objeto `CreateUserDTO`;
    - Pesquisa com o `IRoleRepository` por roles com o mesmo nome e salva em uma lista;
    - Invoca o método `builder()` em `User`, constrói um novo usuário e o salva.
  - [x] Login: Rota `/auth/login`, verbo POST
    - Recebe um objeto `CreateLoginDTO`;
    - Cria um token padrão do Spring com o login e senha do DTO;
    - Invoca o método `authenticate` de `AuthenticationManager` para retornar um objeto `Authentication` com todos os dados necessários para qualquer autenticação;
    - Cria um token JWT com base no login do DTO;
    - Retorna o token JWT, que deve ser utilizado em quaisquer requisições que exijam autorização.

  b) Operações comuns

  - [x] Leitura: Rota `/usuarios`, verbo GET
    - [x] Lista de usuários;
    - [ ] 1 único usuário.
  - [ ] Atualização: verbo PUT
  - [ ] Deleção: verbo DELETE

- [ ] Status: Rota `/status`

  - [x] Criação: Verbo POST
    - Recebe uma entidade `CreateStatusDTO`.
  - [x] Leitura: Verbo GET
    - [x] Lista de status;
    - [ ] 1 único status.
  - [ ] Atualização: Verbo PUT
  - [ ] Deleção: Verbo DELETE

- [ ] Gêneros: Rota `/generos`

  - [x] Criação: Verbo POST
    - Recebe um `CreateGenreDTO`, salva a entidade e retorna para a rota `/generos/{id}` com detalhes da entidade.
  - [x] Leitura: Verbo GET
    - [x] Lista de gêneros;
    - [x] 1 único gênero.
  - [x] Atualização: Verbo PUT
    - Recebe um `UpdateGenreDTO`, salva a entidade e retorna seus dados.
  - [ ] Deleção: Verbo DELETE

- [ ] Empréstimos: [a desenvolver]

## :wrench: Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Sv.Library:

#### **Linguagem**

- **[Java 17](https://docs.oracle.com/en/java/javase/17/)**.

#### **Ferramentas e Frameworks**

- **[Spring Framework](https://spring.io/)**;

  - Módulos:
    - **[Spring Boot](https://spring.io/projects/spring-boot/)**;
    - **[Spring Data JPA](https://spring.io/projects/spring-data-jpa/)**;
    - **[Spring Security](https://spring.io/projects/spring-security/)**.

- **[Flyway](https://flywaydb.org/)**;

- **[Lombok](https://projectlombok.org/)**;

- **[java-jwt](https://github.com/auth0/java-jwt)**;

- **[springdoc-openapi](https://springdoc.org/)**.

#### Utilitários

- Editor: **[Visual Studio Code](https://code.visualstudio.com)**
  - Extensões:
    - **Extension Pack for Java**;
    - **Language Support for Java(TM) by Red Hat**;
    - **Maven for Java**;
- Markdown: **Markdown Preview Enhanced**;

## :book: Como executar

## :mag: Observações

- Vários métodos e classes, especialmente as com prefixo `Genre` e `Status`, estão com código "desatualizado" que será refatorado e melhorado assim que possível, incluindo classes `Service` e melhorias de lógica;

- A nomenclatura das interfaces de `Repository` podem soar estranhas com o prefixo `I`; isso se dá devido à minha experiência com o mundo do C# onde existe este padrão de nomear interfaces com `I` para realizar injeção de dependência e diferenciar das suas implementações. Para mim, essa é uma abordagem que faz sentido e optei por adotá-la no mundo Java.

## :information_desk_person: Autor

## Licença
