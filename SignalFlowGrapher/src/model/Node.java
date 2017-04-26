package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Node {

    private int name;
    private Hashtable<Integer, Edge> edges;

    public Node(int name) {
        this.name = name;
        edges = new Hashtable<>();
    }

    public boolean addEdge(int to, double cost) {
        if (edges.containsKey(to)) {
            return false;
        }
        edges.put(to, new Edge(name, to, cost));
        return true;
    }

    public boolean removeEdge(int to) {
        if (!edges.containsKey(to)) {
            return false;
        }
        edges.remove(to);
        return true;
    }

    public boolean modifyCost(int to, double cost) {
        if (!edges.containsKey(to)) {
            return false;
        }
        edges.get(to).modifyCost(cost);
        return true;
    }

    public ArrayList<Edge> getEdges() {
        return new ArrayList<>(edges.values());
    }

}


