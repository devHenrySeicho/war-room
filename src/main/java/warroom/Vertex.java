package warroom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Edge<TYPE>> getInput() {
        return Collections.unmodifiableList(this.input);
    }

    public List<Edge<TYPE>> getOutput() {
        return Collections.unmodifiableList(this.output);
    }
}
