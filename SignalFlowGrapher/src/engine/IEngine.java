package engine;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IEngine {

    // ADD COMPONENET
    public void addNode(String nodeName);

    public void addEdge(String fromNode, String toNode, double value);

    // MODIFY COMPONENT
    public void modifyNodeName(String oldName, String newName);

    public void modifyEdgeValue(String startNode, String endNode, double newValue);

    // REMOVE COMPONENT

    // CALCULATE TRANSFER FUNCTION
    public double calculateTFFunction(String fromNode, String toNode);

    public ArrayList<ArrayList<String>> getForwardPaths();

    public ArrayList<ArrayList<String>> getLoops();

    public Hashtable<Integer, ArrayList<ArrayList<ArrayList<String>>>> getCombinations();

    public double getDelta();

    public ArrayList<Double> getDeltas();

    public void clearCanvas();

}
