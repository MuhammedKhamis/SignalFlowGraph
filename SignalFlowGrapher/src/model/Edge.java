package model;

public class Edge {

    private int from;
    private int to;
    private double cost;

    public Edge(int from, int to, double cost) {
        this.cost = cost;
        this.to = to;
        this.from = from;
    }

    public void modifyCost(double cost) {
        this.cost = cost;
    }

    public int getSource() {
        return from;
    }

    public int getDestination() {
        return to;
    }

    public Double getCost() {
        return new Double(cost);
    }
}
