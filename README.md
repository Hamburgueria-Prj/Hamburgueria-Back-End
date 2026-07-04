# 🍔 Hamburgueria Local
Sistema de gestão para hamburgueria com módulos de Cardápio, Pedidos e Dashboard.

---

## 📋 Manual de Colaboração Git
Leia antes de fazer qualquer commit. Seguir esse guia garante que o time trabalhe sem conflitos.

---

## 🚀 Configuração Inicial — Faça isso uma única vez
Todo membro do time precisa seguir esses passos antes de começar a trabalhar.

> **👉 Já tem o Git instalado?**
> Se você já usa Git, pula o passo 2 e o passo 4. Só confere se seu nome e e-mail já estão configurados:
> ```bash
> git config --global user.name
> git config --global user.email
> ```
> Se retornar seus dados corretamente, seu fluxo é só este:
> - ✅ Aceita o convite do e-mail
> - ✅ `git clone` da URL do repositório
> - ✅ Entra na `develop` e cria a sua branch de trabalho

---

### 1. Aceite o convite do repositório
Vai chegar um e-mail do GitHub com o convite. Clica em **Accept invitation**.

---

### 2. Instale o Git na sua máquina — pule se já tiver
- **Windows:** baixa em [git-scm.com](https://git-scm.com) e instala com as opções padrão
- **Mac:** abre o terminal e roda `git --version` — se não tiver, ele instala automaticamente
- **Linux:** `sudo apt install git`

---

### 3. Clone o repositório (cria a pasta local)
Abre o terminal (ou Git Bash no Windows) e escolhe onde quer salvar o projeto — por exemplo, na pasta Documentos:

```bash
cd Documentos
git clone https://github.com/Hamburgueria-Prj/hamburgueria-local.git
cd hamburgueria-local
```

> Esse comando cria uma pasta chamada `hamburgueria-local` no seu computador com todos os arquivos do projeto.

---

### 4. Configure sua identidade no Git — pule se já tiver feito
Verifica primeiro se já está configurado:
```bash
git config --global user.name
git config --global user.email
```
Se não retornar nada, configura agora:
```bash
git config user.name "Seu Nome"
git config user.email "seu@email.com"
```

---

### 5. Entre na branch `develop` e crie a sua branch de trabalho

> ⚠️ **ATENÇÃO — leia isso antes de sair criando branch:**
>
> A branch `develop` **já existe no repositório** e é onde está o **projeto base pronto** (estrutura Spring Boot, pom.xml com as dependências e pacotes configurados).
>
> **Você NÃO precisa criar a `develop`.** Você só precisa entrar nela e criar a sua branch de feature a partir dela.

```bash
# 1. Garante que você está com a develop atualizada
git checkout develop
git pull origin develop

# 2. Agora cria a SUA branch a partir da develop
git checkout -b feature/SUA-TAREFA
```

Substitui `SUA-TAREFA` pela sua tarefa:

| Dev | Comando |
|-----|---------|
| Devs 1 e 2 | `git checkout -b feature/cardapio` |
| Devs 3 e 4 | `git checkout -b feature/pedidos` |
| Devs 5 e 6 | `git checkout -b feature/dashboard` |

✅ Pronto! Você está na sua branch, com o projeto base já carregado, pronto para codar.

---

### 6. Configure o banco de dados na sua máquina — obrigatório antes de rodar

> ⚠️ **ATENÇÃO — o `application.properties` NÃO foi configurado no projeto base.**
>
> Isso foi feito de propósito: cada um tem uma senha diferente no MySQL da sua máquina. Você precisa configurar esse arquivo localmente antes de rodar o projeto, caso contrário ele vai dar erro na inicialização.

**Passo 1 — Crie o banco de dados no MySQL**

Abre o MySQL Workbench (ou terminal do MySQL) e executa:
```sql
CREATE DATABASE hamburgueria;
```

> O Hibernate cria as tabelas automaticamente. Você só precisa criar o banco vazio.

**Passo 2 — Configure o `application.properties`**

Dentro do projeto, abre o arquivo:
```
src/main/resources/application.properties
```

E preenche com as suas configurações:
```properties
# Banco de dados MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/hamburgueria
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate — cria/atualiza as tabelas automaticamente
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


# Porta do servidor
server.port=8080
```

> 💡 Substitui `SUA_SENHA_AQUI` pela senha do seu MySQL local. Se não tiver senha configurada, deixa o campo em branco: `spring.datasource.password=`

> 🚫 **Não sobe o `application.properties` com sua senha para o repositório.** Esse arquivo já está no `.gitignore` justamente por isso — cada um configura o seu localmente.

**Passo 3 — Verifique se o projeto sobe**

Na IDE (IntelliJ ou VS Code), rode o projeto e veja se aparece no console:
```
Started HamburgueriaApplication in X seconds
```
Se aparecer esse log, está tudo funcionando. Se der erro, confere se o banco `hamburgueria` foi criado e se a senha está correta.

---

## 🌿 Estrutura de Branches

```
main          ← protegida, só recebe via PR
└── develop   ← ✅ JÁ CRIADA — projeto base está aqui
    ├── feature/cardapio    (Devs 1 e 2)
    ├── feature/pedidos     (Devs 3 e 4)
    └── feature/dashboard   (Devs 5 e 6)
```

| Branch | Descrição | Quem mexe |
|--------|-----------|-----------|
| `main` | 🔒 Versão estável, produção | Ninguém diretamente |
| `develop` | Integração de todas as features — **projeto base aqui** | Somente via PR |
| `feature/cardapio` | Gestão de produtos, preços e categorias | Devs 1 e 2 |
| `feature/pedidos` | Registro de vendas em tempo real | Devs 3 e 4 |
| `feature/dashboard` | Visão geral de vendas e itens mais vendidos | Devs 5 e 6 |

---

## 🚦 Regras do Time
- 🚫 Proibido fazer commit direto na `main` ou na `develop`
- ✅ Obrigatório abrir Pull Request para mergear na `develop`
- 👁 Obrigatório ter pelo menos 1 aprovação antes de mergear
- 💬 Mensagens de commit devem ser descritivas (ver padrão abaixo)
- 👥 Todos devem ter contribuições no histórico

---

## 🔄 Fluxo de Trabalho Diário

```
① Entrar na develop   →   ② Criar sua branch   →   ③ Fazer commits   →   ④ Abrir PR   →   ⑤ Code review e merge
   e dar pull               de feature               descritivos            com descrição
```

### Passo a passo

**1. Antes de começar a codar — sempre atualize sua branch**
```bash
# Atualiza a develop local com o que está no repositório remoto
git checkout develop
git pull origin develop

# Volta para a sua branch de feature
git checkout feature/nome-da-tarefa

# Traz as atualizações da develop para a sua branch (evita conflitos)
git merge develop
```

> 💡 **Dica:** faça isso toda vez que for começar a trabalhar no dia. Evita conflito com o código dos colegas.

**2. Fazer commits enquanto trabalha**
```bash
git add .
git commit -m "feat(cardapio): adiciona entidade Produto"
git push origin feature/nome-da-tarefa
```

**3. Abrir Pull Request**
- Vai no GitHub → **Pull requests** → **New pull request**
- Base: `develop` ← Compare: `feature/sua-branch`
- Escreva uma descrição do que foi feito
- Peça revisão para um colega

**4. Resolver conflitos (se aparecer)**
```bash
git checkout develop
git pull origin develop
git checkout feature/nome-da-tarefa
git merge develop
# resolva os conflitos no VS Code ou IntelliJ
git push origin feature/nome-da-tarefa
```

---

## ✍️ Padrão de Commits

**Formato:** `tipo(escopo): descrição curta`

| Tipo | Quando usar |
|------|-------------|
| `feat` | Nova funcionalidade |
| `fix` | Correção de bug |
| `style` | CSS, formatação, sem lógica |
| `refactor` | Melhoria de código sem mudar comportamento |
| `docs` | Alteração em documentação |
| `chore` | Configuração, dependências |

**Exemplos reais do projeto:**
```
feat(cardapio): adiciona entidade Produto com campos e anotações JPA
feat(cardapio): cria ProdutoRepository com método findByAtivoTrue
fix(pedidos): corrige cálculo do subtotal do item
style(dashboard): ajusta cores do gráfico
refactor(cardapio): separa componente de listagem
docs: atualiza README com fluxo de trabalho
```

---

## ⚡ Comandos Rápidos

```bash
# Ver em qual branch você está
git branch

# Ver status dos arquivos modificados
git status

# Ver histórico de commits
git log --oneline

# Descartar alterações em um arquivo
git checkout -- nome-do-arquivo

# Ver diferença antes de commitar
git diff
```

> 💬 **Chame no grupo antes de forçar qualquer push!**
