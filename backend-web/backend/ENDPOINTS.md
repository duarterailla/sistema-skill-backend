# Documentação dos Endpoints - Sistema Skill

## Autenticação

### Registrar Usuário
- **POST** `/api/auth/register`
- **Body:**
```json
{
  "login": "usuario",
  "password": "senha"
}
```
- **Resposta:**
```json
{"message": "Usuário cadastrado com sucesso"}
```

### Login
- **POST** `/api/auth/login`
- **Body:**
```json
{
  "login": "usuario",
  "password": "senha"
}
```
- **Resposta:**
```json
{"token": "<JWT_TOKEN>"}
```

## Skills

### Listar Skills
- **GET** `/api/skills`
- **Headers:**
  - Authorization: Bearer <token>
- **Resposta:**
```json
[
  {"id": 1, "name": "Java"},
  {"id": 2, "name": "Spring"}
]
```

### Cadastrar Skill
- **POST** `/api/skills`
- **Headers:**
  - Authorization: Bearer <token>
- **Body:**
```json
{"name": "Java"}
```
- **Resposta:**
```json
{"id": 1, "name": "Java"}
```

## Skills do Usuário

### Listar Skills do Usuário
- **GET** `/api/user-skills/{userId}`
- **Headers:**
  - Authorization: Bearer <token>
- **Resposta:**
```json
[
  {"id": 1, "user": {...}, "skill": {...}, "level": 5}
]
```

### Associar Skill ao Usuário
- **POST** `/api/user-skills`
- **Headers:**
  - Authorization: Bearer <token>
- **Body:**
```json
{"userId": 1, "skillId": 2, "level": 5}
```
- **Resposta:**
```json
{"id": 1, "user": {...}, "skill": {...}, "level": 5}
```

### Atualizar Nível da Skill
- **PUT** `/api/user-skills/{userSkillId}`
- **Headers:**
  - Authorization: Bearer <token>
- **Body:**
```json
{"level": 7}
```
- **Resposta:**
```json
{"id": 1, "user": {...}, "skill": {...}, "level": 7}
```

### Excluir Associação
- **DELETE** `/api/user-skills/{userSkillId}`
- **Headers:**
  - Authorization: Bearer <token>
- **Resposta:**
```json
{"message": "Associação removida com sucesso"}
```

## Swagger
Acesse a documentação automática em: `/swagger-ui.html` ou `/swagger-ui/index.html`

---

> **Observação:**
> Exceto o login e cadastro, todos os endpoints exigem o header `Authorization: Bearer <token>` gerado no login.
