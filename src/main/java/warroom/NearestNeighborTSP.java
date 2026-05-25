package warroom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NearestNeighborTSP<TYPE> {
    private final Graph<TYPE> graph;

    public NearestNeighborTSP(Graph<TYPE> graph) {
        if(graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        this.graph = graph;
    }

    public Result<TYPE> solve(TYPE startData) {
        Vertex<TYPE> start = this.graph.getVertex(startData);

        if(start == null) {
            throw new IllegalArgumentException("Start vertex does not exist in the graph.");
        }

        ArrayList<TYPE> route = new ArrayList<TYPE>();
        Set<Vertex<TYPE>> visited = new HashSet<Vertex<TYPE>>();
        Vertex<TYPE> current = start;
        double totalWeight = 0.0;

        route.add(current.getData());
        visited.add(current);

        while(visited.size() < this.graph.getVertexes().size()) {
            Edge<TYPE> nearestEdge = this.findNearestUnvisitedEdge(current, visited);

            if(nearestEdge == null) {
                throw new IllegalStateException("Could not build a complete route from the start vertex.");
            }

            totalWeight += nearestEdge.getWeight();
            current = nearestEdge.getTarget();
            visited.add(current);
            route.add(current.getData());
        }

        if(this.graph.getVertexes().size() == 1) {
            route.add(start.getData());
            return new Result<TYPE>(route, totalWeight);
        }

        Edge<TYPE> returnEdge = this.findEdge(current, start);

        if(returnEdge == null) {
            throw new IllegalStateException("Could not return to the start vertex.");
        }

        totalWeight += returnEdge.getWeight();
        route.add(start.getData());

        return new Result<TYPE>(route, totalWeight);
    }

    public Result<TYPE> solveBestRoute() {
        if(this.graph.getVertexes().isEmpty()) {
            return new Result<TYPE>(new ArrayList<TYPE>(), 0.0);
        }

        Result<TYPE> bestResult = null;

        for(Vertex<TYPE> vertex : this.graph.getVertexes()) {
            try {
                Result<TYPE> currentResult = this.solve(vertex.getData());

                if(bestResult == null || currentResult.getTotalWeight() < bestResult.getTotalWeight()) {
                    bestResult = currentResult;
                }
            } catch(IllegalStateException ignored) {
                // Some starts can get stuck on sparse graphs. Try the next one.
            }
        }

        if(bestResult == null) {
            throw new IllegalStateException("Could not build a complete route from any start vertex.");
        }

        return bestResult;
    }

    private Edge<TYPE> findNearestUnvisitedEdge(Vertex<TYPE> source, Set<Vertex<TYPE>> visited) {
        Edge<TYPE> nearestEdge = null;

        for(Edge<TYPE> edge : source.getOutput()) {
            if(visited.contains(edge.getTarget())) {
                continue;
            }

            if(nearestEdge == null || edge.getWeight() < nearestEdge.getWeight()) {
                nearestEdge = edge;
            }
        }

        return nearestEdge;
    }

    private Edge<TYPE> findEdge(Vertex<TYPE> source, Vertex<TYPE> target) {
        Edge<TYPE> foundEdge = null;

        for(Edge<TYPE> edge : source.getOutput()) {
            if(edge.getTarget() != target) {
                continue;
            }

            if(foundEdge == null || edge.getWeight() < foundEdge.getWeight()) {
                foundEdge = edge;
            }
        }

        return foundEdge;
    }

    public static class Result<TYPE> {
        private final List<TYPE> route;
        private final double totalWeight;

        public Result(List<TYPE> route, double totalWeight) {
            this.route = Collections.unmodifiableList(new ArrayList<TYPE>(route));
            this.totalWeight = totalWeight;
        }

        public List<TYPE> getRoute() {
            return this.route;
        }

        public double getTotalWeight() {
            return this.totalWeight;
        }

        @Override
        public String toString() {
            return "Result{route=" + this.route + ", totalWeight=" + this.totalWeight + "}";
        }
    }
}
