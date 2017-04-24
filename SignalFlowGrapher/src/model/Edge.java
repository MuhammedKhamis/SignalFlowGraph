package model;

public class Edge {

    private int from;
    private int to;
    private int cost;

    public Edge(int from, int to, int cost) {
        this.cost = cost;
        this.to = to;
        this.from = from;
    }

    public void modifyCost(int cost) {
        this.cost = cost;
    }

    public Integer getSource() {
        return new Integer(from);
    }

    public Integer getDestination() {
        return new Integer(to);
    }

    public Integer getCost() {
        return new Integer(cost);
    }
}
