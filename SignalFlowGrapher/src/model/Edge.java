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

    public int getSource() {
        return from;
    }

    public int getDestination() {
        return to;
    }

    public Integer getCost() {
        return new Integer(cost);
    }
}
