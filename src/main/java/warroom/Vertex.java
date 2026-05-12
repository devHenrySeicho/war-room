package warroom;

import java.util.ArrayList;

public class Vertex<TYPE> {
    private TYPE data;
    private ArrayList<Edge<TYPE>> input;
    private ArrayList<Edge<TYPE>> output;

    public Vertex(TYPE value){
        this.data = value;
        this.input = new ArrayList<Edge<TYPE>>();
        this.output = new ArrayList<Edge<TYPE>>();
    }

    public TYPE getData() {
        return data;
    }

    public void setData(TYPE data) {
        this.data = data;
    }

    public void addInput(Edge<TYPE> edge){
        this.input.add(edge);
    }

    public void addOutput(Edge<TYPE> edge){
        this.output.add(edge);
    }
}
