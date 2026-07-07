# Refatoração: remoção da entidade Cliente

Esta versão remove o fluxo de Cliente do backend. Agora o comprador é representado pelo próprio Usuario com perfil CLIENTE.

## Fluxo novo

- Usuario ADMIN: acessa gestão, produtos, pagamentos e pedidos.
- Usuario CLIENTE: faz login, cria pedido e acompanha status.
- Pedido: fica vinculado somente ao campo usuario_id.

## Principais mudanças

- Removidos: Cliente.java, ClienteController.java, ClienteService.java, ClienteRepository.java, ClienteRequest.java e ClienteResponse.java.
- Pedido não possui mais relacionamento com Cliente.
- PedidoRequest não recebe mais clienteId.
- PedidoResponse retorna usuarioId, usuarioNome e usuarioPerfil.
- PedidoService usa apenas UsuarioRepository para vincular o pedido ao usuário logado.
- PedidoController agora também lista pedidos:
  - GET /pedidos
  - GET /pedidos/{id}
  - GET /pedidos/usuario/{usuarioId}
  - POST /pedidos
  - PATCH /pedidos/{id}/status?status=EM_PREPARO

## JSON novo para criar pedido

```json
{
  "usuarioId": 2,
  "tipoPedido": "DELIVERY",
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    }
  ]
}
```

## Banco de dados

O Hibernate com `spring.jpa.hibernate.ddl-auto=update` não remove colunas antigas automaticamente.
Se quiser limpar o banco manualmente depois de confirmar que tudo está funcionando, execute:

```sql
USE hamburgueria;

SET FOREIGN_KEY_CHECKS = 0;

ALTER TABLE pedidos DROP FOREIGN KEY pedidos_ibfk_1;
ALTER TABLE pedidos DROP COLUMN cliente_id;
DROP TABLE clientes;

SET FOREIGN_KEY_CHECKS = 1;
```

O nome da foreign key pode variar. Se o comando `DROP FOREIGN KEY pedidos_ibfk_1` falhar, veja o nome correto com:

```sql
SHOW CREATE TABLE pedidos;
```

Para teste local, também pode simplesmente limpar o banco e deixar o Hibernate recriar a estrutura conforme as entidades atuais.
