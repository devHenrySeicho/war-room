package warroom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph<TYPE>{
    private ArrayList<Vertex<TYPE>> vertexes;
    private ArrayList<Edge<TYPE>> edges;

    public Graph(){
        this.vertexes = new ArrayList<Vertex<TYPE>>();
        this.edges = new ArrayList<Edge<TYPE>>();
    }

    public void addVertex(TYPE data){
        Vertex<TYPE> newVertex = new Vertex<TYPE>(data);
        this.vertexes.add(newVertex);
    }

    public void addEdge(Double weight, TYPE dataSource, TYPE dataTarget){
        Vertex<TYPE> source = this.getVertex(dataSource);
        Vertex<TYPE> target = this.getVertex(dataTarget);
        Edge<TYPE> edge = new Edge<TYPE>(weight, source, target);
        source.addOutput(edge);
        target.addInput(edge);
        this.edges.add(edge);
    }

    public Vertex<TYPE> getVertex(TYPE data){
        Vertex<TYPE> vertex = null;
        for(int i = 0; i < this.vertexes.size(); i++){
            if(this.vertexes.get(i).getData().equals(data)){
                vertex = this.vertexes.get(i);
                break;
            }
        }
        return vertex;
    }

    public List<Vertex<TYPE>> getVertexes(){
        return Collections.unmodifiableList(this.vertexes);
    }

    public List<Edge<TYPE>> getEdges(){
        return Collections.unmodifiableList(this.edges);
    }

    public void saveAsJson(String filePath) throws IOException {
        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"vertices\": [\n");

        for(int i = 0; i < this.vertexes.size(); i++){
            Vertex<TYPE> vertex = this.vertexes.get(i);

            json.append("    { \"data\": \"")
                    .append(escapeJson(String.valueOf(vertex.getData())))
                    .append("\" }");

            if(i < this.vertexes.size() - 1){
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ],\n");
        json.append("  \"edges\": [\n");

        for(int i = 0; i < this.edges.size(); i++){
            Edge<TYPE> edge = this.edges.get(i);

            json.append("    { \"source\": \"")
                    .append(escapeJson(String.valueOf(edge.getSource().getData())))
                    .append("\", \"target\": \"")
                    .append(escapeJson(String.valueOf(edge.getTarget().getData())))
                    .append("\", \"weight\": ")
                    .append(edge.getWeight())
                    .append(" }");

            if(i < this.edges.size() - 1){
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ]\n");
        json.append("}\n");

        Files.writeString(Path.of(filePath), json.toString());
    }

    private String escapeJson(String value) {
        StringBuilder escaped = new StringBuilder();

        for(int i = 0; i < value.length(); i++){
            char character = value.charAt(i);

            switch(character){
                case '\\':
                    escaped.append("\\\\");
                    break;
                case '"':
                    escaped.append("\\\"");
                    break;
                case '\n':
                    escaped.append("\\n");
                    break;
                case '\r':
                    escaped.append("\\r");
                    break;
                case '\t':
                    escaped.append("\\t");
                    break;
                default:
                    escaped.append(character);
                    break;
            }
        }

        return escaped.toString();
    }
}
