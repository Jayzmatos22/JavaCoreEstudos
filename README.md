# Java Core — Exercícios

Exercícios resolvidos durante os estudos de Java Core (01/09/2026 a 04/10/2026),
organizados por assunto. Java 21, sem build tool.

| Pasta | Exercício | Classe |
|---|---|---|
| `comparator` | Fila de triagem hospitalar (prioridade/idade/espera, nullsLast em dois níveis) | `TriagemHospitalar` |
| `streams` | Produto mais barato com estoque | `ProdutoMaisBarato` |
| `streams` | Todos os empatados no preço mínimo | `EmpatadosNoMenorPreco` |
| `groupingby` | Série completa de groupingBy + downstream | `GroupingByExercicios` |
| `groupingby` | groupingBy com usuários/bancos: joining, summarizing, filtering, dois níveis | `GroupingByUsuarios` |
| `collectors` | Média isolada vs summarizingDouble | `EstatisticasSaldo` |
| `enums` | Consultas sobre enum: busca por código, ordenação, índice com toMap | `BancoConsultas` |
| `map` | Série completa de HashMap | `HashMapExercicios` |
| `equalshashcode` | Contrato equals/hashCode quebrado de propósito | `ContratoQuebrado` |
| `sorted` | Série completa de sorted | `SortedExercicios` |
| `excecoes` | Busca que lança, causa, try-with-resources, fail fast | `ExcecoesExercicios` |
| `modelo` | `Produto`, `Usuario`, `BancoEnum` e as massas de dados compartilhadas | — |

## Rodando

PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out sorted.SortedExercicios
```

Bash:

```bash
javac -d out $(find src -name "*.java")
java -cp out sorted.SortedExercicios
```
