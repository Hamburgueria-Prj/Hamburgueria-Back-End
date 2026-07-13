# Alterações de autenticação

Esta versão adiciona autenticação simples por e-mail e senha no backend.

## Regra implementada

- O primeiro usuário cadastrado no banco vira `ADMIN` automaticamente.
- Os próximos usuários cadastrados viram `CLIENTE` automaticamente.

## Endpoints adicionados

### Registrar

`POST /auth/register`

```json
{
  "nome": "Luan",
  "email": "luan@email.com",
  "senha": "123456"
}
```

### Login

`POST /auth/login`

```json
{
  "email": "luan@email.com",
  "senha": "123456"
}
```

## Retorno

```json
{
  "id": 1,
  "nome": "Luan",
  "email": "luan@email.com",
  "perfil": "ADMIN",
  "ativo": true,
  "mensagem": "Login realizado com sucesso."
}
```

## Observação

Se já existir usuário antigo no banco com perfil `ATENDENTE` ou `COZINHA`, limpe a tabela `usuarios` antes de rodar porque o enum agora usa apenas `ADMIN` e `CLIENTE`.
