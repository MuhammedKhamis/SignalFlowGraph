package GuiObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class SFGNode {
	
	//01_ATTRIBUTES
	//*************************************************************
	private Circle circle;
	private Text displayName;
	
	private double cursorOriginalX;
	private double cursorOriginalY;
	
	
	//02_CONSTRUCTOR
	//*************************************************************
	public SFGNode(String keyName) {
		circle = new Circle(150, 150, 10, Color.RED);
		setNodeDragEvent(circle);
		
		displayName=new Text(keyName);
		displayName.translateXProperty().bind(circle.centerXProperty().add(-7));
		displayName.translateYProperty().bind(circle.centerYProperty().add(-15));
	}
	
	//03_METHODS
	//*************************************************************
	
	//Node DRAG EVENT
	private void setNodeDragEvent(Circle shape){
		shape.setOnMousePressed(e ->{
			cursorOriginalX=e.getX();
			cursorOriginalY=e.getY();
		});
		shape.setOnMouseDragged(e ->{
			double deltaX=e.getX()-cursorOriginalX;
			double deltaY=e.getY()-cursorOriginalY;
			
			shape.setCenterX(cursorOriginalX+deltaX);
			shape.setCenterY(cursorOriginalY+deltaY);
		});
	}
	
	//GETTERS
	public Circle getCircle(){
		return circle;
	}
	public Text getDisplayName(){
		return displayName;
	}
	
	
	

}
