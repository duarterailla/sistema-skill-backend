# Sistema Skill - Backend


Este projeto é o backend de um sistema para cadastro, organização e associação de skills (habilidades) a usuários, com suporte a categorias, exportação em PDF e autenticação JWT.

## Tecnologias
- Java 17
- Spring Boot 3.4.8
- PostgreSQL
- JPA/Hibernate
- JWT
- OpenPDF
=======
Sistema para cadastro, organização e associação de skills (habilidades) a usuários, com suporte a categorias, exportação em PDF e autenticação JWT. Ideal para portfólios, empresas ou projetos educacionais.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.4.8**
- **PostgreSQL**
- **JPA/Hibernate**
- **JWT**
- **OpenPDF**

## Instalação e Execução
1. Configure o banco PostgreSQL em `src/main/resources/application.properties`.
2. Execute o script de seed para popular skills e categorias (opcional).
3. Compile e rode:
   ```bash
   ./mvnw clean package
   java -jar target/sistema-skill-0.0.1-SNAPSHOT.jar
   ```
4. O backend estará disponível em `http://localhost:8080`.
>>>>>>> 0545f4d70468d50866fa1dd14cba0fb3d38dc6de

## Estrutura de Pastas
```
src/
  main/
    java/
      com/neki/sistemaskill/
        model/         # Entidades JPA
        repository/    # Repositórios Spring Data
        controller/    # Controllers REST
        service/       # Lógica de negócio
        config/        # Configurações de segurança
    resources/
      application.properties # Configuração do banco e JWT
```

## Principais Endpoints
<<<<<<< HEAD
- `/api/skills` - CRUD de skills
- `/api/skills/categoria/{id}` - Skills por categoria
- `/api/categorias` - CRUD de categorias
- `/api/user-skills` - Associação de skills a usuários
- `/api/user-skills/{userId}` - Skills de um usuário
- `/api/user-skills/{userId}/pdf` - Exporta skills do usuário em PDF
- `/api/usuarios/{userId}` - Dados do usuário
- `/api/auth/**` - Autenticação

## Como rodar
1. Configure o banco PostgreSQL em `src/main/resources/application.properties`.
2. Execute o script de seed para popular skills e categorias (se necessário).
3. Compile e rode:
   ```
   ./mvnw clean package
   java -jar target/sistema-skill-0.0.1-SNAPSHOT.jar
   ```
4. O backend estará disponível em `http://localhost:8080`.
=======
- `GET /api/skills` - Lista todas as skills
- `POST /api/skills` - Cadastra uma nova skill
- `GET /api/skills/categoria/{id}` - Skills por categoria
- `GET /api/categorias` - Lista categorias
- `POST /api/categorias` - Cadastra categoria
- `GET /api/user-skills/{userId}` - Skills de um usuário
- `POST /api/user-skills` - Associa skill ao usuário
- `GET /api/user-skills/{userId}/pdf` - Exporta skills do usuário em PDF
- `GET /api/usuarios/{userId}` - Dados do usuário
- `POST /api/auth/register` - Cadastro de usuário
- `POST /api/auth/login` - Login
>>>>>>> 0545f4d70468d50866fa1dd14cba0fb3d38dc6de

## Exemplo de JSON para cadastro de skill
```json
{
  "nome": "Java",
  "descricao": "Linguagem de programação",
  "imagem_url": "https://exemplo.com/java.png"
}
```

## Segurança
- Endpoints públicos: `/api/auth/**`, `/api/categorias/**`, `/api/skills/**`, `/api/user-skills/**`, `/api/usuarios/**`
- Endpoints protegidos podem ser configurados em `SecurityConfig.java`.
<<<<<<< HEAD
=======
- Autenticação JWT para rotas protegidas.
>>>>>>> 0545f4d70468d50866fa1dd14cba0fb3d38dc6de

## Exportação PDF
- O endpoint `/api/user-skills/{userId}/pdf` gera um relatório das skills do usuário em PDF.

<<<<<<< HEAD
## Observações
- O backend está pronto para integração com frontend React.
- Para dúvidas sobre endpoints, consulte os controllers na pasta `controller`.
=======
## Swagger
Acesse a documentação automática e teste os endpoints em:
- [`/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
- [`/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

## Observações
- Pronto para integração com frontend React ou qualquer outro.
- Para dúvidas sobre endpoints, consulte os controllers na pasta `controller`.
- Para exemplos detalhados de requisições e respostas, veja o arquivo `ENDPOINTS.md`.


>>>>>>> 0545f4d70468d50866fa1dd14cba0fb3d38dc6de
