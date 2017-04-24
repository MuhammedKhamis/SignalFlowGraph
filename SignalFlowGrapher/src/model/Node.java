package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Node {

    private int number;
    private Hashtable<Integer, Edge> edges;

    public Node(int number) {
        this.number = number;
        edges = new Hashtable<>();
    }

    public boolean addEdge(int to, int cost) {
        if (edges.containsKey(to)) {
            return false;
        }
        edges.put(new Integer(to), new Edge(number, to, cost));
        return true;
    }

    public boolean removeEdge(int to) {
        if (!edges.containsKey(to)) {
            return false;
        }
        edges.remove(new Integer(to));
        return true;
    }

    public boolean modifyCost(int to, int cost) {
        if (!edges.containsKey(new Integer(to))) {
            return false;
        }
        edges.get(new Integer(to)).modifyCost(cost);
        return true;
    }

    public ArrayList<Edge> getEdges() {
        return (ArrayList<Edge>) edges.values();
    }

}
