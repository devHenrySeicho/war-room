package warroom;

import java.util.Random;
import java.util.HashSet;

public class Generator {

    public static void generate(Graph<String> graph) {

        Random random = new Random();

        String[] nodes = {
                "a","b","c","d","e","f","g","h","i","j","k","l","m",
                "n","o","p","q","r","s","t","u","v","w","x","y","z"
        };

        for(String node : nodes) {
            graph.addVertex(node);
        }

        HashSet<String> usedEdges = new HashSet<>();

        for(int i = 0; i < nodes.length - 1; i++) {

            String from = nodes[i];
            String to = nodes[i + 1];

            double weight = random.nextInt(11);

            graph.addEdge(weight, from, to);
            graph.addEdge(weight, to, from);

            usedEdges.add(from + "-" + to);
            usedEdges.add(to + "-" + from);
        }

        int extraConnections = 80;

        for(int i = 0; i < extraConnections; i++) {

            String from = nodes[random.nextInt(nodes.length)];
            String to = nodes[random.nextInt(nodes.length)];

            if(from.equals(to)) {
                i--;
                continue;
            }

            String edgeKey = from + "-" + to;

            if(usedEdges.contains(edgeKey)) {
                i--;
                continue;
            }

            double weight = random.nextInt(11);

            graph.addEdge(weight, from, to);
            graph.addEdge(weight, to, from);

            usedEdges.add(from + "-" + to);
            usedEdges.add(to + "-" + from);
        }

        System.out.println("TSP-friendly graph gerado com sucesso!");
    }
}