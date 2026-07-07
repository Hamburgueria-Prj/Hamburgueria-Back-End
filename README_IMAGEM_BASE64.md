# Imagem Base64 no Produto

Este backend usa imagem do produto em Base64 para facilitar o trabalho local, sem pasta de upload e sem nuvem.

Campo novo no JSON de produto:

```json
{
  "nome": "X-Bacon artesanal",
  "descricao": "Hambúrguer com bacon",
  "preco": 19.90,
  "categoria": "HAMBURGUER",
  "imagemBase64": "data:image/jpeg;base64,/9j/4AAQSkZJRg..."
}
```

A coluna no MySQL fica como `imagem_base64 LONGTEXT`.

Se o Hibernate não criar automaticamente, rode:

```sql
USE hamburgueria;
ALTER TABLE produtos ADD COLUMN imagem_base64 LONGTEXT;
```

Use imagens leves para o trabalho, de preferência até 500 KB.
