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
        g.addNode(6);
        g.addNode(7);
        g.addNode(8);
        g.addNode(9);
        ////////////////////////////////
        g.addEdge(0, 1, 1.0);
        g.addEdge(1, 2, 1.0);
        g.addEdge(2, 3, 1.0);
        g.addEdge(3, 4, 1.0);
        g.addEdge(4, 5, 1.0);
        g.addEdge(5, 6, 1.0);
        g.addEdge(6, 7, 1.0);
        g.addEdge(7, 8, 1.0);
        g.addEdge(8, 9, 1.0);
        ////////////////////////////////
        g.addEdge(2, 1, -1.0);
        g.addEdge(4, 3, -1.0);
        g.addEdge(6, 5, -1.0);
        g.addEdge(8, 7, -1.0);
        ////////////////////////////////
        g.removeNode(9);
        g.solve(0, 8);
        ///////////////////////////
        System.out.println(g.getGain());

    }

}
