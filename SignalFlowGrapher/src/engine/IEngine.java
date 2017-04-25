package engine;

public interface IEngine {
	
	//ADD COMPONENET
	public void addNode(String nodeName);
	
	public void addEdge(String fromNode, String toNode, double value);
	
	//MODIFY COMPONENT
	public void modifyNodeName(String oldName, String newName);
	
	public void modifyEdgeValue(String startNode, String endNode, double newValue);
	
	//REMOVE COMPONENT
	
	//CALCULATE TRANSFER FUNCTION
	public double calculateTFFunction(String fromNode, String toNode);		

}
