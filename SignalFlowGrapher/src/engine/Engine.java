package engine;

import java.util.HashMap;

import controller.Graph;

public class Engine implements IEngine {

    private HashMap<String, Integer> map;

    private int counter;

    private Graph graph;

    public Engine() {
        // TODO Auto-generated constructor stub
        map = new HashMap<>();
        counter = 0;
        graph = Graph.getInstance();

    }

    @Override
    public void addNode(String nodeName) {
        // TODO Auto-generated method stub
        map.put(nodeName, counter);
        graph.addNode(counter);
        counter++;

    }

    @Override
    public void addEdge(String fromNode, String toNode, double value) {
        // TODO Auto-generated method stub
        graph.addEdge(map.get(fromNode), map.get(toNode), value);

    }

    @Override
    public void modifyNodeName(String oldName, String newName) {
        // TODO Auto-generated method stub
        Integer val = map.remove(oldName);
        map.put(newName, val);

    }

    @Override
    public void modifyEdgeValue(String startNode, String endNode, double newValue) {
        // TODO Auto-generated method stub
        graph.modifyCost(map.get(startNode), map.get(endNode), newValue);

    }

    @Override
    public double calculateTFFunction(String fromNode, String toNode) {
        // TODO Auto-generated method stub
        graph.solve(map.get(fromNode), map.get(toNode));
        return graph.getGain();
    }

}
