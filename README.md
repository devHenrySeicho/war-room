# WarRoom

Projeto em Java para representar grafos ponderados, gerar um grafo de exemplo e aplicar uma heuristica para o problema do Caixeiro Viajante usando o metodo Nearest Neighbor.

## Requisitos

- Java 25
- Maven, opcional

O projeto foi configurado no `pom.xml` com `maven.compiler.source` e `maven.compiler.target` em Java 25.

## Estrutura

```text
src/main/java/warroom/
|-- Edge.java
|-- Generator.java
|-- Graph.java
|-- Main.java
|-- NearestNeighborTSP.java
`-- Vertex.java
```

Principais classes:

- `Graph<TYPE>`: representa o grafo, armazenando vertices e arestas.
- `Vertex<TYPE>`: representa um vertice e suas arestas de entrada e saida.
- `Edge<TYPE>`: representa uma aresta ponderada entre dois vertices.
- `Generator`: gera um grafo de exemplo com vertices de `a` ate `z`.
- `NearestNeighborTSP<TYPE>`: resolve o Caixeiro Viajante com a heuristica Nearest Neighbor.
- `Main`: gera o grafo e salva sua representacao em `graph.json`.

## Como Executar

Compile e execute diretamente com `javac` e `java`:

```bash
javac -d target/classes src/main/java/warroom/*.java
java -cp target/classes warroom.Main
```

A execucao cria ou atualiza o arquivo:

```text
graph.json
```

Se o Maven estiver instalado:

```bash
mvn clean package
java -cp target/classes warroom.Main
```

## Caixeiro Viajante com Nearest Neighbor

A classe `NearestNeighborTSP` usa uma heuristica gulosa:

1. Comeca em um vertice inicial.
2. Escolhe sempre a aresta de menor peso para um vertice ainda nao visitado.
3. Repete ate visitar todos os vertices.
4. Retorna ao vertice inicial para fechar o ciclo.

Exemplo de uso:

```java
Graph<String> graph = new Graph<String>();

graph.addVertex("A");
graph.addVertex("B");
graph.addVertex("C");

graph.addEdge(2.0, "A", "B");
graph.addEdge(5.0, "A", "C");
graph.addEdge(2.0, "B", "A");
graph.addEdge(1.0, "B", "C");
graph.addEdge(5.0, "C", "A");
graph.addEdge(1.0, "C", "B");

NearestNeighborTSP<String> tsp = new NearestNeighborTSP<String>(graph);
NearestNeighborTSP.Result<String> result = tsp.solve("A");

System.out.println(result.getRoute());
System.out.println(result.getTotalWeight());
```

Saida esperada:

```text
[A, B, C, A]
8.0
```

Tambem e possivel testar todos os vertices como ponto inicial e ficar com a menor rota encontrada pela heuristica:

```java
NearestNeighborTSP.Result<String> bestResult = tsp.solveBestRoute();
```

## Observacoes

- Nearest Neighbor e uma heuristica, portanto nao garante a rota otima.
- O grafo e tratado como direcionado, pois cada aresta possui origem e destino.
- Para simular um grafo nao direcionado, adicione as duas direcoes da aresta.
- O `Generator` ja adiciona arestas nos dois sentidos.
- Em grafos esparsos, a heuristica pode nao conseguir visitar todos os vertices ou retornar ao inicio.
