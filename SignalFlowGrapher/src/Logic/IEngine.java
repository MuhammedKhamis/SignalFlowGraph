package Logic;

public interface IEngine {

    // ADD COMPONENET
    public void addNode(String nodeName);

    public void addEdge(String fromNode, String toNode, double value);

    // MODIFY COMPONENT
    public void modifyNodeName(String oldName, String newName);

    public void modifyEdgeValue(String startNode, String endNode, double newValue);

    // REMOVE COMPONENT
    public void clearCanvas();

    // CALCULATE TRANSFER FUNCTION
    public double calculateTFFunction(String fromNode, String toNode);

    public String getForwardPaths();

    public String getIndividualLoops();

    public String getNonTouchingLoops();

    public String getDeltas();

}
