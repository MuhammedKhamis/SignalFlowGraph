package model;

public class Edge {

    private String from;
    private String to;
    private int cost;

    public Edge(String from, String to, int cost) {
        this.cost = cost;
        this.to = to;
        this.from = from;
    }

    public void modifyCost(int cost) {
        this.cost = cost;
    }

    public String getSource() {
        return from;
    }

    public String getDestination() {
        return to;
    }

    public Integer getCost() {
        return new Integer(cost);
    }
}
