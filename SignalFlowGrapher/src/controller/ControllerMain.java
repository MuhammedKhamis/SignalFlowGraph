package controller;

public class ControllerMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Graph g = Graph.getInstance();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 5, 1);
        g.addEdge(1, 3, 1);
        g.addEdge(1, 4, 1);
        g.addEdge(3, 3, -1);
        g.addEdge(3, 2, -1);
        g.addEdge(2, 1, -1);
        g.solve(0, 5);

    }

}
