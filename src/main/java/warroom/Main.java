package warroom;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("a");
        graph.addVertex("b");

        graph.addEdge(2.0,"a", "b");
        graph.addEdge(5.0,"b", "a");
    }
}
