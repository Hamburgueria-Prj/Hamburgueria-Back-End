# 🍔 Hamburgueria Local

Sistema de gestão para hamburgueria com módulos de Cardápio, Pedidos e Dashboard.

---

## 📋 Manual de Colaboração Git

> Leia antes de fazer qualquer commit. Seguir esse guia garante que o time trabalhe sem conflitos.

---

## 🚀 Configuração Inicial — Faça isso uma única vez

> Todo membro do time precisa seguir esses passos antes de começar a trabalhar.

---

### 👉 Já tem o Git instalado?

Se você já usa Git, **pula o passo 2 e o passo 4**. Só confere se seu nome e e-mail já estão configurados:

```bash
git config --global user.name
git config --global user.email
```

Se retornar seus dados corretamente, seu fluxo é só este:

1. ✅ Aceita o convite do e-mail
2. ✅ `git clone` da URL do repositório
3. ✅ Cria a branch de trabalho

---

### 1. Aceite o convite do repositório

Vai chegar um e-mail do GitHub com o convite. Clica em **Accept invitation**.

### 2. Instale o Git na sua máquina — pule se já tiver

- **Windows:** baixa em [git-scm.com](https://git-scm.com) e instala com as opções padrão
- **Mac:** abre o terminal e roda `git --version` — se não tiver, ele instala automaticamente
- **Linux:** `sudo apt install git`

### 3. Clone o repositório (cria a pasta local)

Abre o terminal (ou Git Bash no Windows) e escolhe onde quer salvar o projeto — por exemplo, na pasta `Documentos`:

```bash
cd Documentos
git clone https://github.com/SEU-USUARIO/hamburgueria-local.git
```

> Substitui `SEU-USUARIO` pelo usuário real do dono do repositório.  
> Esse comando vai **criar uma pasta chamada `hamburgueria-local`** no seu computador com todos os arquivos do projeto.

### 4. Configure sua identidade no Git — pule se já tiver feito

Verifica primeiro se já está configurado:

```bash
git config --global user.name
git config --global user.email
```

Se não retornar nada, configura agora:

```bash
cd hamburgueria-local
git config user.name "Seu Nome"
git config user.email "seu@email.com"
```

### 5. Crie sua branch de trabalho

```bash
git checkout develop
git checkout -b feature/SUA-TAREFA
```

Substitui `SUA-TAREFA` pela sua tarefa:

| Dev | Comando |
|-----|---------|
| Devs 1 e 2 | `git checkout -b feature/cardapio` |
| Devs 3 e 4 | `git checkout -b feature/pedidos` |
| Devs 5 e 6 | `git checkout -b feature/dashboard` |

✅ **Pronto! Sua pasta local está configurada e você já está na branch certa.**

---

## 🌿 Estrutura de Branches

```
main          ← protegida, só recebe via PR
└── develop   ← branch de integração do time
    ├── feature/cardapio    (Devs 1 e 2)
    ├── feature/pedidos     (Devs 3 e 4)
    └── feature/dashboard   (Devs 5 e 6)
```

| Branch | Descrição | Quem mexe |
|--------|-----------|-----------|
| `main` | 🔒 Versão estável, produção | Ninguém diretamente |
| `develop` | Integração de todas as features | Somente via PR |
| `feature/cardapio` | Gestão de produtos, preços e categorias | Devs 1 e 2 |
| `feature/pedidos` | Registro de vendas em tempo real | Devs 3 e 4 |
| `feature/dashboard` | Visão geral de vendas e itens mais vendidos | Devs 5 e 6 |

---

## 🚦 Regras do Time

- 🚫 **Proibido** fazer commit direto na `main` ou na `develop`
- ✅ **Obrigatório** abrir Pull Request para mergear na `develop`
- 👁 **Obrigatório** ter pelo menos 1 aprovação antes de mergear
- 💬 Mensagens de commit devem ser descritivas (ver padrão abaixo)
- 👥 Todos devem ter contribuições no histórico

---

## 🔄 Fluxo de Trabalho Diário

```
① Criar branch     →     ② Fazer commits     →     ③ Abrir PR     →     ④ Code review e merge
   da develop               descritivos               com descrição
```

### Passo a passo

**1. Começar uma nova feature**
```bash
git checkout develop
git pull origin develop
git checkout -b feature/nome-da-tarefa
```

**2. Fazer commits enquanto trabalha**
```bash
git add .
git commit -m "feat(cardapio): adiciona filtro por categoria"
git push origin feature/nome-da-tarefa
```

**3. Abrir Pull Request**
- Vai no GitHub → **Pull requests** → **New pull request**
- Base: `develop` ← Compare: `feature/sua-branch`
- Escreva uma descrição do que foi feito
- Peça revisão para um colega

**4. Manter sua branch atualizada (evitar conflitos)**
```bash
git checkout develop
git pull origin develop
git checkout feature/nome-da-tarefa
git merge develop
# resolva conflitos se houver
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

### Exemplos reais do projeto

```bash
feat(cardapio): adiciona filtro por categoria
fix(pedidos): corrige total do carrinho
style(dashboard): ajusta cores do gráfico
refactor(cardapio): separa componente de listagem
docs: atualiza README com fluxo de trabalho
```

---

## 🍔 Divisão de Telas

### Cardápio — Devs 1 e 2
> Gestão completa de produtos, preços e categorias de alimentos.

Branch: `feature/cardapio`

### 🛒 Pedidos — Devs 3 e 4
> Tela de registro de vendas rápidas com integração em tempo real.

Branch: `feature/pedidos`

### 📊 Dashboard — Devs 5 e 6
> Visão geral de vendas do dia e itens mais vendidos.

Branch: `feature/dashboard`

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

---

## ❓ Dúvida?

Chame no grupo antes de forçar qualquer push! 💬
