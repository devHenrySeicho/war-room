package warroom;

public class Edge<TYPE> {
    private double weight;
    private Vertex<TYPE> source;
    private Vertex<TYPE> target;

    public Edge(Double weight, Vertex<TYPE> source, Vertex<TYPE> target){
        this.weight = weight;
        this.source = source;
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex<TYPE> getSource() {
        return source;
    }

    public void setSource(Vertex<TYPE> source) {
        this.source = source;
    }

    public Vertex<TYPE> getTarget() {
        return target;
    }

    public void setTarget(Vertex<TYPE> target) {
        this.target = target;
    }

}
