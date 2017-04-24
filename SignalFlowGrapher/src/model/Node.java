package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Node {

    private String name;
    private Hashtable<String, Edge> edges;

    public Node(String name) {
        this.name = name;
        edges = new Hashtable<>();
    }

    public boolean addEdge(String to, int cost) {
        if (edges.containsKey(to)) {
            return false;
        }
        edges.put(to, new Edge(name, to, cost));
        return true;
    }

    public boolean removeEdge(String to) {
        if (!edges.containsKey(to)) {
            return false;
        }
        edges.remove(to);
        return true;
    }

    public boolean modifyCost(String to, int cost) {
        if (!edges.containsKey(to)) {
            return false;
        }
        edges.get(to).modifyCost(cost);
        return true;
    }

    public ArrayList<Edge> getEdges() {
        return (ArrayList<Edge>) edges.values();
    }

}
