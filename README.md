# 🏥 Sistema Hospitalar de Triagem — Refatoração SOLID

> **Exercício Prático — Arquitetura de Sistemas**
> SENAI/SC · Turma AI MIDS 2024/2 INT1 · Docente: Lucas Sousa dos Santos

---

## 📋 Sumário

- [Contextualização](#-contextualização)
- [O Caos — Antipattern (Código Problemático)](#-o-caos--antipattern-código-problemático)
- [A Ordem — Solução Refatorada](#-a-ordem--solução-refatorada)
- [Arquitetura em Camadas](#-arquitetura-em-camadas)
- [Princípios SOLID Aplicados](#-princípios-solid-aplicados)
- [Padrão de Projeto: Strategy](#-padrão-de-projeto-strategy)
- [Estrutura de Pacotes](#-estrutura-de-pacotes)
- [Diagrama de Classes](#-diagrama-de-classes)
- [Fluxo de Execução](#-fluxo-de-execução)
- [Como Executar](#-como-executar)
- [Diferencial: Interação com o Usuário](#-diferencial-interação-com-o-usuário)

---

## 🎯 Contextualização

Sistemas legados monolíticos são uma realidade dolorosa no mercado de software. São aplicações que funcionam — até o dia em que precisam crescer. Qualquer alteração num bloco de lógica pode cascatear erros por todo o sistema, tornando a manutenção um processo arriscado e caro.

Este projeto simula exatamente esse cenário: um **Sistema de Triagem Hospitalar** que foi construído de forma frágil e acoplada (Antipattern) e depois **completamente refatorado** para uma arquitetura limpa, desacoplada e extensível, aderindo aos **5 princípios SOLID** e ao **Padrão Strategy** do catálogo GoF.

---

## 💥 O Caos — Antipattern (Código Problemático)

O código original (versão "caótica") concentrava toda a lógica em uma única classe, violando múltiplos princípios simultaneamente. O exemplo abaixo ilustra como **não** se deve estruturar um sistema:

```java
// ❌ ANTIPATTERN — Deus Objeto: faz tudo, sabe tudo, acopla tudo
public class TriagemCaotica {

    public void atender(String nome, int bpm, String tipo) {

        // ❌ Violação SRP: Esta classe persiste, notifica E classifica. São 3 responsabilidades.
        List<String> banco = new ArrayList<>();
        banco.add(nome + " - " + bpm + " BPM");

        // ❌ Violação OCP: Para adicionar um novo protocolo (ex: "Eletivo"),
        // sou OBRIGADO a abrir e modificar esta classe.
        // ❌ Violação LSP: Não há contrato (interface). Nenhuma estratégia é substituível.
        if (tipo.equals("emergencia")) {
            System.out.println("CODIGO VERMELHO! Atendimento imediato para: " + nome);
        } else if (tipo.equals("urgencia")) {
            System.out.println("CODIGO AMARELO! Atendimento em 1 hora para: " + nome);
        }

        // ❌ Violação DIP: Dependemos de uma implementação concreta de notificação,
        // não de uma abstração. Trocar de "System.out" para e-mail exigiria mexer aqui.
        System.out.println("NOTIFICAÇÃO: Paciente " + nome + " registrado.");

        // ❌ Violação ISP: Se essa classe implementasse uma interface genérica com
        // métodos de persistência, notificação e protocolo juntos, subclasses seriam
        // forçadas a implementar métodos que não fazem sentido para elas.
    }
}
```

### Violações Identificadas

| Princípio | Descrição da Violação |
|---|---|
| **SRP** | Uma única classe persiste dados, exibe notificações e decide o protocolo clínico |
| **OCP** | Adicionar `ProtocoloEletivo` obriga a abrir e editar a classe existente |
| **LSP** | Sem interfaces, nenhum protocolo pode ser substituído de forma polimórfica |
| **ISP** | Interface hipotética gigante forçaria implementações desnecessárias |
| **DIP** | A lógica de negócio depende diretamente de detalhes de infraestrutura (`System.out`, `ArrayList`) |

---

## ✅ A Ordem — Solução Refatorada

A refatoração decompôs a "classe deus" em componentes coesos, cada um com **uma única razão para mudar**, conectados por **abstrações (interfaces)** em vez de implementações concretas.

---

## 🏛️ Arquitetura em Camadas

O sistema segue uma arquitetura em **3 camadas profissionais**, onde a dependência flui sempre de fora para dentro (do concreto para o abstrato):

```
┌─────────────────────────────────────────────────────────┐
│                    CAMADA: DOMAIN                       │
│  (O núcleo do sistema. Não depende de nada externo.)    │
│                                                         │
│   IProtocoloAtendimento   IPacienteRepository           │
│   INotification           Paciente (Entidade)           │
└────────────────────────┬────────────────────────────────┘
                         │ depende de (abstrações)
┌────────────────────────▼────────────────────────────────┐
│                    CAMADA: SERVICE                      │
│  (Orquestra o fluxo. Conhece apenas interfaces.)        │
│                                                         │
│   TriagemService                                        │
└────────────────────────┬────────────────────────────────┘
                         │ é implementado por
┌────────────────────────▼────────────────────────────────┐
│                    CAMADA: INFRA                        │
│  (Detalhes técnicos: I/O, banco de dados, notificação.) │
│                                                         │
│   ProtocoloEmergencia   RepositoryPacienteMemoria       │
│   ProtocoloUrgencia     PainelNotification              │
│   AppConfig (Fábrica/Wiring)                            │
└─────────────────────────────────────────────────────────┘
```

> **Regra de Ouro:** A camada `domain` jamais importa nada das camadas `service` ou `infra`. O fluxo de dependência é unidirecional.

---

## 🔩 Princípios SOLID Aplicados

### S — Single Responsibility Principle (Princípio da Responsabilidade Única)

> *"Uma classe deve ter apenas um motivo para mudar."*

Cada classe possui exatamente **uma responsabilidade** bem definida:

| Classe | Única Responsabilidade |
|---|---|
| `Paciente` | Representar os dados de domínio de um paciente |
| `PainelNotification` | Formatar e exibir mensagens no console |
| `RepositoryPacienteMemoria` | Persistir e recuperar pacientes em memória |
| `ProtocoloEmergencia` | Encapsular a regra médica de emergência (Código Vermelho) |
| `ProtocoloUrgencia` | Encapsular a regra médica de urgência (Código Amarelo) |
| `TriagemService` | **Orquestrar** o atendimento, delegando para suas ferramentas |
| `AppConfig` | Montar (instanciar e injetar) as dependências do sistema |

```java
// ✅ SRP: PainelNotification só sabe exibir mensagens. Nada mais.
public class PainelNotification implements INotification {
    @Override
    public void notificar(String mensagem) {
        System.out.println("\n------------------------------------");
        System.out.println("\nNOTIFICACAO: " + mensagem);
        System.out.println("\n------------------------------------");
    }
}
```

---

### O — Open/Closed Principle (Princípio do Aberto/Fechado)

> *"Classes devem estar abertas para extensão, mas fechadas para modificação."*

O `TriagemService` nunca precisará ser modificado para suportar um novo protocolo clínico. Basta criar uma nova classe que implemente `IProtocoloAtendimento` e injetá-la:

```java
// ✅ OCP: Criamos ProtocoloEletivo sem tocar em nenhuma linha do TriagemService.
public class ProtocoloEletivo implements IProtocoloAtendimento {
    @Override
    public String priorizar(Paciente paciente) {
        return "CODIGO VERDE! Agendamento eletivo para: " + paciente.getNome();
    }
}

// No Main, apenas injetamos a nova estratégia:
hospital.configurarProtocolo(new ProtocoloEletivo());
```

---

### L — Liskov Substitution Principle (Princípio da Substituição de Liskov)

> *"Objetos de uma subclasse devem poder substituir objetos da superclasse sem alterar o comportamento esperado do programa."*

Como `ProtocoloEmergencia` e `ProtocoloUrgencia` honram **integralmente** o contrato de `IProtocoloAtendimento`, qualquer um deles pode ser injetado no `TriagemService` e o sistema se comportará corretamente. A troca é transparente para o serviço.

```java
// ✅ LSP: TriagemService não percebe a diferença. Ele apenas chama .priorizar().
hospital.configurarProtocolo(new ProtocoloEmergencia()); // funciona
hospital.configurarProtocolo(new ProtocoloUrgencia());   // também funciona, sem surpresas
```

---

### I — Interface Segregation Principle (Princípio da Segregação de Interfaces)

> *"Nenhuma classe deve ser forçada a implementar métodos que não utiliza."*

Em vez de uma interface monolítica (`ISistemaHospitalar` com métodos `salvar`, `notificar` e `priorizar`), criamos **três interfaces minúsculas e coesas**, cada uma com um único propósito:

```java
// ✅ ISP: Três contratos independentes. Ninguém implementa o que não precisa.

public interface INotification {
    void notificar(String mensagem); // Só notifica
}

public interface IPacienteRepository {
    void salvar(Paciente paciente);  // Só persiste
    List<Paciente> listarTodos();
}

public interface IProtocoloAtendimento {
    String priorizar(Paciente paciente); // Só classifica
}
```

---

### D — Dependency Inversion Principle (Princípio da Inversão de Dependência)

> *"Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações."*

O `TriagemService` (módulo de alto nível) declara suas dependências como **interfaces**, nunca como classes concretas. A `AppConfig` atua como **Composition Root**, resolvendo quais implementações reais serão utilizadas:

```java
// ✅ DIP: TriagemService depende de ABSTRAÇÕES, não de implementações.
public class TriagemService {
    private final INotification notification;           // abstração
    private final IPacienteRepository pacienteRepository; // abstração
    private IProtocoloAtendimento protocolo;             // abstração

    public TriagemService(INotification notification, IPacienteRepository repo) {
        this.notification = notification;
        this.pacienteRepository = repo;
    }
}

// AppConfig: o único lugar do sistema que conhece as classes concretas.
public class AppConfig {
    private final INotification notification = new PainelNotification();
    private final IPacienteRepository pacienteRepository = new RepositoryPacienteMemoria();

    public TriagemService triagemService() {
        return new TriagemService(notification, pacienteRepository);
    }
}
```

---

## ♟️ Padrão de Projeto: Strategy

O **Padrão Strategy** (GoF — Behavioral) foi aplicado para eliminar estruturas condicionais frágeis (`if/else` ou `switch`) na lógica de priorização. O padrão permite que o **algoritmo de classificação seja trocado em tempo de execução** sem qualquer alteração no código do serviço.

### Participantes do Padrão

```
«interface»
IProtocoloAtendimento          ← Context Interface (Strategy)
    + priorizar(Paciente): String
         ▲               ▲
         │               │
ProtocoloEmergencia   ProtocoloUrgencia   ← Concrete Strategies
    (Código Vermelho)   (Código Amarelo)

TriagemService                           ← Context
    - protocolo: IProtocoloAtendimento
    + configurarProtocolo(IProtocoloAtendimento)
    + realizarAtendimento(Paciente)
```

### Troca de Estratégia em Tempo de Execução

```java
// O Context (TriagemService) não sabe qual regra está sendo aplicada.
// A estratégia é injetada de fora, em tempo de execução.

hospital.configurarProtocolo(new ProtocoloEmergencia());
hospital.realizarAtendimento(pacienteEmergencia);
// Output: "CODIGO VERMELHO! BPM: 130 Atendimento imediato para: João"

hospital.configurarProtocolo(new ProtocoloUrgencia());
hospital.realizarAtendimento(pacienteUrgencia);
// Output: "CODIGO AMARELO! BPM: 95 Atendimento em ate 1 hora para: Maria"
```

---

## 📁 Estrutura de Pacotes

```
src/main/java/org/example/
│
├── domain/                          ← Núcleo: Entidades e Contratos
│   ├── INotification.java           ← Contrato de notificação (ISP)
│   ├── IPacienteRepository.java     ← Contrato de persistência (ISP + DIP)
│   ├── IProtocoloAtendimento.java   ← Contrato Strategy (ISP + OCP)
│   └── Paciente.java                ← Entidade de domínio (imutável)
│
├── service/                         ← Regras de Negócio (Orquestração)
│   ├── TriagemService.java          ← Serviço principal (DIP + SRP + OCP)
│   └── strategy/
│       ├── ProtocoloEmergencia.java ← Strategy Concreta: Código Vermelho (SRP + LSP)
│       └── ProtocoloUrgencia.java   ← Strategy Concreta: Código Amarelo (SRP + LSP)
│
├── infra/                           ← Detalhes de Infraestrutura
│   ├── PainelNotification.java      ← Implementação de INotification (SRP)
│   ├── RepositoryPacienteMemoria.java ← Implementação de IPacienteRepository (SRP)
│   └── config/
│       └── AppConfig.java           ← Composition Root / Fábrica (DIP)
│
└── Main.java                        ← Ponto de entrada + Interação com usuário
```

---

## 📊 Diagrama de Classes

```
┌──────────────────────┐     ┌──────────────────────────┐
│   «interface»        │     │   «interface»             │
│  INotification       │     │  IPacienteRepository      │
│──────────────────────│     │──────────────────────────│
│ + notificar(String)  │     │ + salvar(Paciente)        │
└──────────┬───────────┘     │ + listarTodos(): List     │
           │ implements      └───────────┬──────────────┘
           │                             │ implements
┌──────────▼───────────┐     ┌───────────▼──────────────┐
│  PainelNotification  │     │ RepositoryPacienteMemoria │
│──────────────────────│     │──────────────────────────│
│ + notificar(String)  │     │ - pacientes: List         │
└──────────────────────┘     │ + salvar(Paciente)        │
                             │ + listarTodos(): List     │
                             └──────────────────────────┘

┌──────────────────────┐
│   «interface»        │
│ IProtocoloAtendimento│
│──────────────────────│
│ + priorizar(Paciente)│
└──────┬──────────┬────┘
       │          │ implements
       │  ┌───────▼────────────┐   ┌─────────────────────┐
       │  │ ProtocoloEmergencia│   │  ProtocoloUrgencia   │
       │  │────────────────────│   │─────────────────────│
       │  │+ priorizar(Pac.)   │   │+ priorizar(Pac.)     │
       │  └────────────────────┘   └─────────────────────┘
       │
┌──────▼────────────────────────────────────────────────┐
│                    TriagemService                      │
│───────────────────────────────────────────────────────│
│ - notification: INotification                         │
│ - pacienteRepository: IPacienteRepository             │
│ - protocolo: IProtocoloAtendimento                    │
│───────────────────────────────────────────────────────│
│ + configurarProtocolo(IProtocoloAtendimento)          │
│ + realizarAtendimento(Paciente)                       │
└───────────────────────────────────────────────────────┘

┌──────────────────────┐         ┌──────────────────────┐
│      AppConfig       │ cria    │      Paciente         │
│──────────────────────│         │──────────────────────│
│ + triagemService()   │─────►   │ - nome: String        │
└──────────────────────┘         │ - frequenciaCardiaca  │
                                 │ + getNome(): String   │
                                 │ + getFrequenciaCardica│
                                 └──────────────────────┘
```

---

## 🔄 Fluxo de Execução

O diagrama abaixo descreve o fluxo de uma chamada ao método `realizarAtendimento`:

```
Main
 │
 ├─► AppConfig.triagemService()
 │       └─► new TriagemService(PainelNotification, RepositoryPacienteMemoria)
 │
 ├─► hospital.configurarProtocolo(new ProtocoloEmergencia())
 │       └─► Injeta a estratégia concreta no campo 'protocolo'
 │
 └─► hospital.realizarAtendimento(paciente)
         │
         ├─► 1. pacienteRepository.salvar(paciente)
         │       └─► RepositoryPacienteMemoria.salvar() → lista.add(paciente)
         │
         ├─► 2. protocolo.priorizar(paciente)
         │       └─► ProtocoloEmergencia.priorizar() → "CODIGO VERMELHO! BPM: ..."
         │
         └─► 3. notification.notificar(resultado)
                 └─► PainelNotification.notificar() → System.out.println(...)
```

> **Princípio da Delegação:** O `TriagemService` não executa nenhuma dessas tarefas diretamente. Ele apenas **coordena** — delega cada responsabilidade à ferramenta certa.

---

## ▶️ Como Executar

### Pré-requisitos

- Java 11+ (JDK)
- Maven ou IDE com suporte a projetos Java (IntelliJ IDEA recomendado)

### Compilação e execução via terminal

```bash
# Compilar todos os arquivos
javac -d out $(find src -name "*.java")

# Executar
java -cp out org.example.Main
```

### Execução via IntelliJ IDEA

1. Abra o projeto no IntelliJ IDEA
2. Localize `Main.java` em `src/main/java/org/example/`
3. Clique com o botão direito → **Run 'Main.main()'**

---

## 🖥️ Diferencial: Interação com o Usuário

O sistema implementa um **menu interativo via `Scanner`**, simulando o fluxo real de uma recepção hospitalar em tempo de execução:

```
=== SISTEMA HOSPITALAR ===

------------------------------------
Escolha o tipo de atendimento:
1 - Emergencia (Risco de vida)
2 - Urgencia (Moderado)
3 - Sair
Opcao: 1

Nome do paciente: João Silva
Frequencia cardiaca: 130

------------------------------------

NOTIFICACAO: CODIGO VERMELHO! BPM: 130 Atendimento imediato para: João Silva

------------------------------------
 Atendimento finalizado com sucesso!
```

### Mecanismos de Robustez Implementados

| Mecanismo | Descrição |
|---|---|
| `lerLinhaSegura()` | Verifica `hasNextLine()` antes de ler, evitando `NoSuchElementException` |
| `lerBpm()` | Trata `NumberFormatException` e valida `bpm > 0` em loop |
| Validação de nome em branco | Loop garante que o campo nome não seja submetido vazio |
| Programação Defensiva no Repository | `Collections.unmodifiableList()` impede modificações externas acidentais na lista interna |

---

## 📌 Resumo dos Padrões e Princípios

| Conceito | Onde está aplicado |
|---|---|
| **SRP** | `PainelNotification`, `RepositoryPacienteMemoria`, `TriagemService`, Estratégias |
| **OCP** | `IProtocoloAtendimento` + novas strategies sem alterar `TriagemService` |
| **LSP** | `ProtocoloEmergencia` e `ProtocoloUrgencia` são intercambiáveis |
| **ISP** | 3 interfaces segregadas: `INotification`, `IPacienteRepository`, `IProtocoloAtendimento` |
| **DIP** | `TriagemService` depende de interfaces; `AppConfig` resolve as implementações |
| **Strategy (GoF)** | Protocolos clínicos encapsulados como estratégias substituíveis em runtime |
| **Composition Root** | `AppConfig` centraliza a montagem de dependências |
| **Injeção de Dependência** | Construtores recebem abstrações prontas (manual DI) |

---

*Atividade desenvolvida para a Unidade Curricular de Arquitetura de Sistemas — SENAI/SC E CENTRO-WEG*
