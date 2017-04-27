package Application;


import java.util.ArrayList;
import java.util.HashMap;

import GuiObjects.SFGEdge;
import GuiObjects.SFGNode;
import Logic.Engine;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;

public class ElementInitializer {

	
	//A_ADD GRAPH COMPONENT
	//*****************************************************
	private Label addComponentLabel;
	
	private Button addNodeButton;
	private TextField addNodeName;
	
	private Button addPathButton;
	private TextField addPathFrom;
	private TextField addPathTo;
	private TextField addPathValue;
	
	
	//D_CALCULATE TRANSFER FUNCTION
	//*****************************************************
	private Label calculateTransferFunctionLabel;
	
	private TextField calculateTFInputNodeName;
	private TextField calculateTFOutputNodeName;
	private Button calculateTFButton;
	private Button clearCanvasButton;
	private Button showSystemDetails;
	private Label resultLabel;
	private Label resultValue;
	
	//C_MODIFY NODE
	//*****************************************************
	private Label modifyNodeLabel;
	
	private TextField modifyNodeValue;
	private Button modifyNodeButton;
	private Button removeNodeButton;
	
	
	//B_MODIFY EDGE
	//*****************************************************
	private Label modifyEdgeLabel;
	
	private TextField modifyEdgeValue;
	private Button modifyEdgeButton;
	private Button removeEdgeButton;
	
	
	
	
	
	//G_LAYOUT ELEMENTS
	//*****************************************************
	private BorderPane layout;
	private Pane canvas;
	
	private HashMap<String, SFGNode> nodeList;
	private ArrayList<SFGEdge> edgeList;
	private Circle selectedNode;
	private QuadCurve selectedCurve;
	
	private Engine appEngine;
	
	
	
	
	//***********************************************************************
	public void initialize(){
		
		//A_ADD GRAPH COMPONENT
		//*****************************************************
		addComponentLabel=new Label("ADD COMPONENT");
		
		addNodeButton=new Button("ADD Node");
		addNodeName=new TextField("node name");
		
		addPathButton=new Button("ADD Path");
		addPathFrom=new TextField("from");
		addPathTo=new TextField("to");
		addPathValue=new TextField("value");

		
		//B_CALCULATE TRANSFER FUNCTION
		//*****************************************************
		calculateTransferFunctionLabel=new Label("CALCULATE TRANSFER FUNCTION");
		
		calculateTFInputNodeName=new TextField("in node");
		calculateTFOutputNodeName=new TextField("out node");
		
		calculateTFButton=new Button("Calculate");
		clearCanvasButton=new Button("CLEAR");
		showSystemDetails=new Button("DETAILS");
		resultLabel=new Label("T.F = ");
		resultValue=new Label("12387");
		
		
		//C_MODIFY NODE COMPONENT
		//*****************************************************
		modifyNodeLabel=new Label("MODIFY COMPONENT (NODE)");
		
		modifyNodeValue=new TextField("value");
		modifyNodeButton=new Button("UPDATE");
		removeNodeButton=new Button("DELETE");
		
		
		//D_MODIFY EDGE COMPONENT
		//*****************************************************
		modifyEdgeLabel=new Label("MODIFY COMPONENT (EDGE)");
		
		modifyEdgeValue=new TextField("value");
		modifyEdgeButton=new Button("UPDATE");
		removeEdgeButton=new Button("DELETE");

		
		//E_LAYOUT ELEMENTS
		//*****************************************************
		canvas=new Pane();
		layout=new BorderPane();
		
		
		nodeList=new HashMap<String, SFGNode>();
		edgeList=new ArrayList<>();
		
		appEngine=new Engine();
		 

	}


	
	//03_GETTERS
	//*************************************************************
	//*************************************************************
	public Label getAddComponentLabel() {
		return addComponentLabel;
	}

	public Button getAddNodeButton() {
		return addNodeButton;
	}

	public TextField getAddNodeName() {
		return addNodeName;
	}

	public Button getAddPathButton() {
		return addPathButton;
	}

	public TextField getAddPathFrom() {
		return addPathFrom;
	}

	public TextField getAddPathTo() {
		return addPathTo;
	}

	public TextField getAddPathValue() {
		return addPathValue;
	}

	public Label getCalculateTransferFunctionLabel() {
		return calculateTransferFunctionLabel;
	}

	public Button getCalculateTFButton() {
		return calculateTFButton;
	}

	public TextField getCalculateTFInputNodeName() {
		return calculateTFInputNodeName;
	}

	public TextField getCalculateTFOutputNodeName() {
		return calculateTFOutputNodeName;
	}

	public Button getClearCanvasButton() {
		return clearCanvasButton;
	}
	
	public Button getShowSystemDetails() {
		return showSystemDetails;
	}

	public Label getResultLabel() {
		return resultLabel;
	}

	public Label getResultValue() {
		return resultValue;
	}

	public Label getModifyNodeLabel() {
		return modifyNodeLabel;
	}

	public TextField getModifyNodeValue() {
		return modifyNodeValue;
	}

	public Button getModifyNodeButton() {
		return modifyNodeButton;
	}

	public Button getRemoveNodeButton() {
		return removeNodeButton;
	}

	public Label getModifyEdgeLabel() {
		return modifyEdgeLabel;
	}

	public TextField getModifyEdgeValue() {
		return modifyEdgeValue;
	}

	public Button getModifyEdgeButton() {
		return modifyEdgeButton;
	}

	public Button getRemoveEdgeButton() {
		return removeEdgeButton;
	}

	public BorderPane getLayout() {
		return layout;
	}

	public Pane getCanvas() {
		return canvas;
	}

	public HashMap<String, SFGNode> getNodeList() {
		return nodeList;
	}

	public ArrayList<SFGEdge> getEdgeList() {
		return edgeList;
	}

	public Circle getSelectedNode() {
		return selectedNode;
	}

	public QuadCurve getSelectedCurve() {
		return selectedCurve;
	}

	public Engine getAppEngine() {
		return appEngine;
	}



	

	



	




	


	
	
}
