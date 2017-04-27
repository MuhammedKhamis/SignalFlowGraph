package Application;


import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class LayoutInitializer {

	//01_ATTRIBUTES
	//*************************************************************
	private ElementInitializer appElements;
	private VBox modifyNode;
	private VBox modifyEdge;
		
		
	//02_CONSTRUCTOR
	//*************************************************************
	public LayoutInitializer(ElementInitializer appElements){
		this.appElements=appElements;
	}
		

	//03_METHODS
	//*************************************************************
	public void initialize(){
				
		//A_ADD GRAPH COMPONENT
		//*****************************************************
		VBox addComponent=new VBox();
		//appElements.getAddComponentLabel().setStyle("-fx-font-weight: bold; -fx-text-fill: 	#5F9EA0;");
		addComponent.getChildren().add(appElements.getAddComponentLabel());
		
		HBox tmp11=new HBox();
		appElements.getAddNodeName().setMaxWidth(115);
		appElements.getAddNodeButton().setPrefWidth(80);
		tmp11.setSpacing(15);
		tmp11.getChildren().addAll(appElements.getAddNodeName(), appElements.getAddNodeButton());
		
		HBox tmp12=new HBox();
		appElements.getAddPathFrom().setMaxWidth(50);
		appElements.getAddPathTo().setMaxWidth(50);
		appElements.getAddPathValue().setMaxWidth(50);
		appElements.getAddPathButton().setPrefWidth(80);
		tmp12.setSpacing(15);
		tmp12.getChildren().addAll(appElements.getAddPathFrom(), appElements.getAddPathTo(), appElements.getAddPathButton());
		
		addComponent.getChildren().addAll(tmp11, tmp12,appElements.getAddPathValue());
		addComponent.setSpacing(10);
		addComponent.setPadding(new Insets(30,20,10,20));
		
		//B_CALCULATE TRANSFER FUNCTION
		//*****************************************************
		VBox calculateTF=new VBox();
		calculateTF.getChildren().add(appElements.getCalculateTransferFunctionLabel());
		
		HBox tmp41=new HBox();
		appElements.getCalculateTFInputNodeName().setPrefWidth(80);
		appElements.getCalculateTFOutputNodeName().setPrefWidth(80);
		tmp41.setSpacing(15);
		tmp41.getChildren().addAll(appElements.getCalculateTFInputNodeName(), appElements.getCalculateTFOutputNodeName());
		calculateTF.getChildren().add(tmp41);
		
		HBox tmp52=new HBox();
		tmp52.setSpacing(10);
		//appElements.getClearCanvasButton().setPrefWidth(100);
		tmp52.getChildren().add(appElements.getCalculateTFButton());
		tmp52.getChildren().add(appElements.getShowSystemDetails());
		tmp52.getChildren().add(appElements.getClearCanvasButton());
		calculateTF.getChildren().add(tmp52);
		
		HBox tmp53=new HBox();
		tmp53.setSpacing(10);
		tmp53.getChildren().addAll(appElements.getResultLabel(), appElements.getResultValue());
		calculateTF.getChildren().add(tmp53);
		
		calculateTF.setSpacing(10);
		calculateTF.setPadding(new Insets(0,20,20,20));
		

		//C_MODIFY NODE
		//*****************************************************
		modifyNode=new VBox();
		modifyNode.getChildren().add(appElements.getModifyNodeLabel());
		
		HBox tmp31=new HBox();
		appElements.getModifyNodeValue().setMaxWidth(100);
		appElements.getModifyNodeButton().setPrefWidth(80);
		tmp31.setSpacing(15);
		tmp31.getChildren().addAll(appElements.getModifyNodeValue(), appElements.getModifyNodeButton());
		
		appElements.getRemoveNodeButton().setPrefWidth(80);
		modifyNode.getChildren().addAll(tmp31, appElements.getRemoveNodeButton());
		modifyNode.setSpacing(10);
		modifyNode.setPadding(new Insets(10,20,5,20));
		
		

		//D_MODFY EDGE
		//*****************************************************
		modifyEdge=new VBox();
		modifyEdge.getChildren().addAll(appElements.getModifyEdgeLabel());
		
		HBox tmp21=new HBox();
		
		appElements.getModifyEdgeValue().setMaxWidth(100);
		appElements.getModifyEdgeButton().setPrefWidth(80);
		tmp21.setSpacing(15);
		tmp21.getChildren().addAll(appElements.getModifyEdgeValue(), appElements.getModifyEdgeButton());
		
		appElements.getRemoveEdgeButton().setPrefWidth(80);
		modifyEdge.getChildren().addAll(tmp21, appElements.getRemoveEdgeButton());
		modifyEdge.setSpacing(10);
		modifyEdge.setPadding(new Insets(5,20,15,20));
		
		
		//ENTIRE SIDE PANEL
		//*****************************************************
		//*****************************************************
		modifyNode.setDisable(true);
		modifyEdge.setDisable(true);
		
		VBox sidePanel=new VBox();
		sidePanel.getChildren().addAll(addComponent, calculateTF, modifyNode, modifyEdge);
		sidePanel.setMinWidth(250);
		sidePanel.setMaxWidth(250);
		sidePanel.setStyle("-fx-background-color: #dddddd;");
		//*****************************************************
		//*****************************************************
		
		//E_DRAWING CANVAS
		//*****************************************************
		appElements.getCanvas().setMinSize(600, 400);
		appElements.getCanvas().setClip(new Rectangle(3000,3000));
		appElements.getCanvas().setPadding(new Insets(30,30,30,30));
		
		HBox drawingCanvas=new HBox();
		drawingCanvas.getChildren().add(appElements.getCanvas());
		drawingCanvas.setStyle("-fx-background-color: white;");
		
		
		//*****************************************************
		appElements.getLayout().setLeft(sidePanel);
		appElements.getLayout().setCenter(drawingCanvas);
		appElements.getLayout().setPrefSize(900, 550);

	}


	
	
	
	//03_GETTERS
	//*************************************************************
	//*************************************************************
	public VBox getModifyNode() {
		return modifyNode;
	}

	public VBox getModifyEdge() {
		return modifyEdge;
	}
	
}
