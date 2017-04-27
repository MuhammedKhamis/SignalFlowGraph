package Application;

import GuiObjects.SFGEdge;
import GuiObjects.SFGNode;
import javafx.scene.shape.Shape;

public class ActionListenerInitializer {
	
	
	
	//01_ATTRIBUTES
	//*************************************************************
	private ElementInitializer appElements;
	private LayoutInitializer layout;
	private SFGNode highlightedNode;
	private SFGEdge highlightedEdge;
	
	
	//02_CONSTRUCTOR
	//*************************************************************
	public ActionListenerInitializer(ElementInitializer appElements, LayoutInitializer layout){
		this.appElements=appElements;
		this.layout=layout;
	}
	

	//03_METHODS
	//*************************************************************
	public void initialize(){

		//ADD NEW NODE BUTTON
		//**********************************
		appElements.getAddNodeButton().setOnMouseClicked(e ->{
			
    		String nodeName=getNodeName();
    		if(!appElements.getNodeList().containsKey(nodeName)){
    			
    			SFGNode node=new SFGNode(nodeName);
        		appElements.getNodeList().put(nodeName, node);
        		appElements.getCanvas().getChildren().addAll(node.getCircle(), node.getDisplayName());
        		resetTextFields();
        		//add node in back-end
        		appElements.getAppEngine().addNode(nodeName);
        		
        		setNodeHighlightingEvent(node, node.getCircle());
        		setNodeHighlightingEvent(node, node.getDisplayName());

    		}else{
    			//display duplicate node MSG
    		}
    		
		});
		
		//ADD NEW EDGE BUTTON
		//**********************************
		appElements.getAddPathButton().setOnMouseClicked(e ->{
	    		String fromNodeName=appElements.getAddPathFrom().getText();
	    		String toNodeName=appElements.getAddPathTo().getText();
	    		
	    		SFGNode fromNode=appElements.getNodeList().get(fromNodeName);
	    		SFGNode toNode=appElements.getNodeList().get(toNodeName);
	    		
	    		//check nodes don't exist
	    		if((fromNode!=null)&&(toNode!=null)){
	    			
	    			double value=Double.valueOf(appElements.getAddPathValue().getText());
		    	    SFGEdge edge=new SFGEdge(fromNode, toNode, value);
		    	    
		    	    //check duplicate edges
		    	    if(!edgeExists(fromNodeName, toNodeName)){
		    	    	//check self loop condition
		    	    	if(!fromNodeName.equalsIgnoreCase(toNodeName)){
		    	    		//generic case
			    	    	appElements.getCanvas().getChildren().removeAll(fromNode.getCircle(), toNode.getCircle());
				    		appElements.getCanvas().getChildren().addAll(edge.getEdge(), edge.getDisplayValue(), edge.getArrowShape(), fromNode.getCircle(), toNode.getCircle());
			    	    }else{
			    	    	//self loop case
			    	    	appElements.getCanvas().getChildren().remove(fromNode.getCircle());
				    		appElements.getCanvas().getChildren().addAll(edge.getEdge(), edge.getDisplayValue(), edge.getArrowShape(), fromNode.getCircle());
			    	    }
		    	    	
			    		//back-end implementation
			    	    appElements.getEdgeList().add(edge);
			    		appElements.getAppEngine().addEdge(fromNodeName, toNodeName, value);
			    		
			    		
			    		setEdgeHighLightEvent(edge, edge.getEdge());
			    		setEdgeHighLightEvent(edge, edge.getDisplayValue());
		    	    }else{
		    	    	appElements.getResultValue().setText("EDGE ALREADY EXISTS!!");
		    	    }
	    		}else{
	    			appElements.getResultValue().setText("NO SUCH NODE(S)!!");
	    		}
	    		
	    	    
	    	    resetTextFields();

    	});
		
		
		//CALCULATE TF BUTTON
		//**********************************
		appElements.getCalculateTFButton().setOnMouseClicked(e ->{
			String fromNode=appElements.getCalculateTFInputNodeName().getText();
    		String toNode=appElements.getCalculateTFOutputNodeName().getText();
			double result=appElements.getAppEngine().calculateTFFunction(fromNode, toNode);
			
			appElements.getResultValue().setText(result+"");
			resetTextFields();
		});
		
		//CLEAR CANVAS BUTTON
		//**********************************
		appElements.getClearCanvasButton().setOnMouseClicked(e ->{
			
			appElements.getNodeList().clear();
			appElements.getEdgeList().clear();
			appElements.getCanvas().getChildren().clear();
			appElements.getAppEngine().clearCanvas();
			resetTextFields();
		});
		
		//UPDATE NODE BUTTON
		//**********************************
		appElements.getModifyNodeButton().setOnMouseClicked(e ->{
			String oldName=highlightedNode.getDisplayName().getText();
			String newName=appElements.getModifyNodeValue().getText();
			if(!appElements.getNodeList().containsKey(newName)){
				appElements.getNodeList().remove(oldName);
				appElements.getNodeList().put(newName, highlightedNode);
				highlightedNode.getDisplayName().setText(newName);
				resetTextFields();
				
				highlightedNode=null;
				//modify in back-end
				appElements.getAppEngine().modifyNodeName(oldName, newName);
				layout.getModifyNode().setDisable(true);
			}
			
		});
		
		//UPDATE PATH BUTTON
		//**********************************
		appElements.getModifyEdgeButton().setOnMouseClicked(e ->{
			String newValue=appElements.getModifyEdgeValue().getText();

			highlightedEdge.getDisplayValue().setText(newValue);
			resetTextFields();
			//modify in back-end
			appElements.getAppEngine().modifyEdgeValue(highlightedEdge.getStartNode(), highlightedEdge.getEndNode(), Double.parseDouble(newValue));
			highlightedEdge=null;
			layout.getModifyEdge().setDisable(true);
		});
		
		//SHOW SYSTEM DETAILS
		//**********************************
		appElements.getShowSystemDetails().setOnMouseClicked(e ->{
			String forwardPaths=appElements.getAppEngine().getForwardPaths();
			String individualLoops=appElements.getAppEngine().getIndividualLoops();
			String nonTouchingLoops=appElements.getAppEngine().getNonTouchingLoops();
			String deltas=appElements.getAppEngine().getDeltas();
			PopupWindow popup=new PopupWindow(forwardPaths, individualLoops, nonTouchingLoops, deltas);
			popup.display();
		});
		
		
		//REMOVE NODE BUTTON
		//**********************************

		
		
		//REMOVE PATH BUTTON
		//**********************************
		
		

		

	}	
	
	
	//04_PRIVATE METHODS
	//***************************************************************************
	//***************************************************************************

	private void setNodeHighlightingEvent(SFGNode selectedNode, Shape shape){
		shape.setOnMouseClicked(e ->{
			highlightedNode=selectedNode;
			if(e.getClickCount()==2){
				layout.getModifyEdge().setDisable(true);
				if(layout.getModifyNode().isDisabled()){
					layout.getModifyNode().setDisable(false);
					appElements.getModifyNodeValue().setText(highlightedNode.getDisplayName().getText());
				}else{
					layout.getModifyNode().setDisable(true);
				}
			}	
			resetTextFields();
		});
	}
	
	
	private void setEdgeHighLightEvent(SFGEdge selectedEdge, Shape shape){
		shape.setOnMouseClicked(e ->{
			highlightedEdge=selectedEdge;
			if(e.getClickCount()==2){
				layout.getModifyNode().setDisable(true);
				if(layout.getModifyEdge().isDisabled()){
					layout.getModifyEdge().setDisable(false);
					appElements.getModifyEdgeValue().setText(highlightedEdge.getDisplayValue().getText());
				}else{
					layout.getModifyEdge().setDisable(true);
				}
			}
			resetTextFields();
		});
	}
	
	
	private void resetTextFields(){
		appElements.getAddNodeName().setText("node name");
		appElements.getAddPathFrom().setText("from");
		appElements.getAddPathTo().setText("to");
		appElements.getAddPathValue().setText("value");
		
		appElements.getCalculateTFInputNodeName().setText("in node");
		appElements.getCalculateTFOutputNodeName().setText("out node");
		
		appElements.getModifyEdgeValue().setText("value");
		
		
	}

	
	private String getNodeName(){
		String nodeName=appElements.getAddNodeName().getText();
		if(nodeName.equalsIgnoreCase("")){
			String id=appElements.getNodeList().size()+"";
			nodeName="n"+id;
		}
		return nodeName;
	}
	
	private boolean edgeExists(String fromNode, String toNode){
		for(SFGEdge edge:appElements.getEdgeList()){
			String tmpFromName=edge.getStartNode();
			String tmpToName=edge.getEndNode();
			if(fromNode.equalsIgnoreCase(tmpFromName)){
				if(toNode.equalsIgnoreCase(tmpToName)){
					return true;
				}
			}
		}
		return false;
	}
	
	
}
