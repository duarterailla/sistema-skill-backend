# Sistema Skill - Backend

Este projeto é o backend de um sistema para cadastro, organização e associação de skills (habilidades) a usuários, com suporte a categorias, exportação em PDF e autenticação JWT.

## Tecnologias
- Java 17
- Spring Boot 3.4.8
- PostgreSQL
- JPA/Hibernate
- JWT
- OpenPDF

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

## Exportação PDF
- O endpoint `/api/user-skills/{userId}/pdf` gera um relatório das skills do usuário em PDF.

## Observações
- O backend está pronto para integração com frontend React.
- Para dúvidas sobre endpoints, consulte os controllers na pasta `controller`.

