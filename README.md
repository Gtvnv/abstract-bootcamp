# Abstraindo um Bootcamp usando POO em Java

Desafio de projeto da [DIO](https://www.dio.me/): modelar um Bootcamp aplicando
os quatro pilares da Programação Orientada a Objetos em Java — **Abstração**,
**Encapsulamento**, **Herança** e **Polimorfismo** — e evoluir o domínio além da
implementação de referência.

## Visão geral

Um **Bootcamp** é uma trilha de aprendizado com período definido que agrega
vários **conteúdos** e os **devs** inscritos. Cada dev progride pelos conteúdos,
acumula **XP** e sobe de **nível**. O bootcamp ainda gera um **ranking** dos devs
por XP.

## Modelo de domínio

```
            ┌────────────────────┐
            │  Conteudo (abstr.) │  calcularXp() : double   ← Abstração
            └─────────┬──────────┘
                      │ herança
   ┌────────┬─────────┼──────────┬──────────┐
   ▼        ▼         ▼          ▼          ▼
 Curso   Mentoria   Desafio   Projeto    (…)      ← Polimorfismo em calcularXp()

 Bootcamp ──◆ possui 0..* ──► Conteudo
 Bootcamp ──◆ possui 0..* ──► Dev
 Dev      ──► inscreve-se em ─► Bootcamp
 Dev      ──► tem um ─────────► Nivel (enum, derivado do XP)
```

## Regras de XP (Polimorfismo)

O XP padrão de base (`XP_PADRAO`) é **10**. Cada tipo de conteúdo tem sua regra:

| Conteúdo | Regra de `calcularXp()` | Exemplo |
| --- | --- | --- |
| `Curso` | `10 × cargaHoraria` | 8h → 80 XP |
| `Mentoria` | `10 + 20` (bônus fixo) | 30 XP |
| `Desafio` | `10 × 1,5 × dificuldade` (1–5) | dif. 3 → 45 XP |
| `Projeto` | `10 × 5 + 10 × (equipe − 1)` | equipe 3 → 70 XP |

## Níveis e badges

O nível do dev é derivado automaticamente do XP acumulado (enum `Nivel`):

| Nível | XP mínimo | Emblema |
| --- | --- | --- |
| INICIANTE | 0 | 🌱 |
| JUNIOR | 100 | 🚀 |
| PLENO | 300 | ⭐ |
| SENIOR | 600 | 🏆 |
| MESTRE | 1000 | 👑 |

## Onde cada pilar aparece

| Pilar | Onde |
| --- | --- |
| **Abstração** | `Conteudo` é abstrata e declara `calcularXp()` e `tipo()` sem implementá-los. |
| **Encapsulamento** | Campos privados; setters validam o estado (carga horária > 0, dificuldade 1–5, campos obrigatórios). |
| **Herança** | `Curso`, `Mentoria`, `Desafio` e `Projeto` estendem `Conteudo`. |
| **Polimorfismo** | Cada subclasse sobrescreve `calcularXp()`/`tipo()`; `Dev` e `Bootcamp` somam XP sem conhecer o tipo concreto. |

## Estrutura

```
src/
└── br/com/dio/desafio/
    ├── Main.java
    └── dominio/
        ├── Conteudo.java      (classe abstrata)
        ├── Curso.java
        ├── Mentoria.java
        ├── Desafio.java       (evolução)
        ├── Projeto.java       (evolução)
        ├── Nivel.java         (enum de níveis/badges)
        ├── Bootcamp.java
        └── Dev.java
```

## Como executar

Pré-requisito: JDK 17 ou superior.

Linux/macOS:

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out br.com.dio.desafio.Main
```

Windows (PowerShell):

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
# garante acentos e emojis corretos no console
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
java "-Dstdout.encoding=UTF-8" -cp out br.com.dio.desafio.Main
```

## Saída esperada

```
XP total disponível no bootcamp: 265.0
============================================================
Camila | Nível: 🚀 JUNIOR | XP: 120,0 | Progresso: 40% (2/5 concluídos)
João   | Nível: 🚀 JUNIOR | XP: 265,0 | Progresso: 100% (5/5 concluídos)
Alice  | Nível: 🚀 JUNIOR | XP: 150,0 | Progresso: 60% (3/5 concluídos)
============================================================
Bootcamp: Bootcamp Java Developer
Período: ... a ...
Conteúdos: 5 | XP disponível: 265.0
Ranking de devs:
  1. João  | ... | XP: 265,0 | Progresso: 100%
  2. Alice | ... | XP: 150,0 | Progresso: 60%
  3. Camila| ... | XP: 120,0 | Progresso: 40%
============================================================
Validação impediu estado inválido: A carga horária deve ser maior que zero.
```

## Evoluções implementadas sobre a referência

- Novos tipos de conteúdo: `Desafio` (por dificuldade) e `Projeto` (bônus por equipe).
- Enum `Nivel` com faixas de XP e emblemas.
- `Dev` com nível, progresso percentual, `resumo()` e `concluirTudo()`.
- `Bootcamp` com API fluente (`adicionarConteudo`), `ranking()`, `relatorio()` e XP total disponível.
- Validações de estado em todos os setters (encapsulamento real).

## Próximas ideias

- Persistência dos dados (arquivo/banco) e testes automatizados (JUnit).
- Certificado ao concluir 100% do bootcamp.
- Pré-requisitos entre conteúdos (um desafio que exige um curso concluído).
