package Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupWindow {
	
	//01_ATTRIBUTES
	//*************************************************************
	VBox layout;
	
	Label forwardPath;
	TextArea forwardPathContent;
	
	Label individualLoops;
	TextArea individualLoopsContent;
	
	Label nonTouchingLoop;
	TextArea nonTouchingLoopContent;
	
	Label delta;
	TextArea deltaContent;
	
	Button closeWindow;
	
	
	//01_CONSTRUCTOR
	//*************************************************************
	public PopupWindow(String forwardPathValues, String indvidualLoopValues, String nonTouchingLoopValues, String deltaValues){
		
		layout=new VBox();
		
		forwardPath=new Label("FORWARD PATHS");
		forwardPathContent=new TextArea(forwardPathValues);
		
		individualLoops=new Label("INDIVIDUAL LOOPS");
		individualLoopsContent=new TextArea(indvidualLoopValues);
		
		nonTouchingLoop=new Label("NON-TOUCHING LOOPS");
		nonTouchingLoopContent=new TextArea(nonTouchingLoopValues);
		
		delta=new Label("DELTA VALUES");
		deltaContent=new TextArea(deltaValues);
		
		closeWindow=new Button("DONE");
	}
	
	
	
	//02_PUBLIC METHODS
	//*************************************************************
	public void display(){
		//01_Initiali Stuff*****************************
		Stage window=new Stage();
		//window.initStyle(StageStyle.UNDECORATED);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setWidth(500); //window.setHeight(300);
		//**********************************************
				
		initializeLayout();
		closeWindow.setOnMouseClicked(e ->{
			window.close();
		});
		
		//03_Final Scene********************************
		Scene scene=new Scene(layout);
		window.setScene(scene);
		window.setResizable(false);
		window.showAndWait();
		//**********************************************
	}
	
	
	
	//03_PRIVATE METHODS
	//*************************************************************
	private void initializeLayout(){
		//layout.setStyle("-fx-background-color: #E5502F;");
		layout.setSpacing(10);
		layout.setPadding(new Insets(30,20,50,20));
		
		forwardPathContent.setPrefHeight(60);
		individualLoopsContent.setPrefHeight(60);
		nonTouchingLoopContent.setPrefHeight(60);
		deltaContent.setPrefHeight(60);
		
		layout.getChildren().addAll(forwardPath, forwardPathContent);
		layout.getChildren().addAll(individualLoops, individualLoopsContent);
		layout.getChildren().addAll(nonTouchingLoop, nonTouchingLoopContent);
		layout.getChildren().addAll(delta, deltaContent);
		layout.getChildren().add(closeWindow);
		
		forwardPathContent.setEditable(false);
		individualLoopsContent.setEditable(false);
		nonTouchingLoopContent.setEditable(false);
		deltaContent.setEditable(false);

	}
	

}
