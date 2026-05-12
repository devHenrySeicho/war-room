package warroom;

import java.util.ArrayList;

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
}
