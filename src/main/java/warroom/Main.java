package warroom;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Graph<String> graph = new Graph<>();

        Generator.generate(graph);
        graph.saveAsJson("graph.json");

        System.out.println("Graph saved as graph.json");
    }
}
