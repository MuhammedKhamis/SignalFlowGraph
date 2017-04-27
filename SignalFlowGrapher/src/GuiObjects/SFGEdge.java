package GuiObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class SFGEdge {

	//01_ATTRIBUTES
	//*************************************************************
	private QuadCurve edge;
	private Text displayValue;
	private String startNode;
	private String endNode;
	private Arrow arrow;
	
	private double cursorOriginalX;
	private double cursorOriginalY;
	
	
	//02_CONSTRUCTOR
	//*************************************************************
	public SFGEdge(SFGNode fromNode, SFGNode toNode, double value) {
		
		edge= new QuadCurve();
		
		Circle c1=fromNode.getCircle();
		Circle c2=toNode.getCircle();
		startNode=fromNode.getDisplayName().getText();
		endNode=toNode.getDisplayName().getText();

		edge.startXProperty().bind(c1.centerXProperty());
		edge.startYProperty().bind(c1.centerYProperty());
		edge.endXProperty().bind(c2.centerXProperty());
		edge.endYProperty().bind(c2.centerYProperty());
        
		edge.setControlX((c1.getCenterX()+c2.getCenterX())/2.0);
		edge.setControlY((c1.getCenterY()+c2.getCenterY())/2.0);
		
		
		displayValue=new Text(value+"");
		displayValue.translateXProperty().bind(edge.controlXProperty());
		displayValue.translateYProperty().bind(edge.controlYProperty());
		
		
		edge.setStroke(Color.BLACK);
		edge.setStrokeWidth(3.5);
		edge.setFill( null);

		setEdgeDragEvent(edge);
		//setEdgeDragEvent(displayValue);
		
		arrow=new Arrow(edge);
		
		//detect change in circle for edge
		c1.centerXProperty().addListener(e ->{
			arrow.updateRotate();
		});
		c1.centerYProperty().addListener(e ->{
			arrow.updateRotate();
		});
		c2.centerXProperty().addListener(e ->{
			arrow.updateRotate();
		});
		c2.centerYProperty().addListener(e ->{
			arrow.updateRotate();
		});

	}
	
	//03_METHODS
	//*************************************************************
	
	//PATH DRAG EVENT
	private void setEdgeDragEvent(Shape shape){
		shape.setOnMousePressed(e ->{
			cursorOriginalX=e.getX();
			cursorOriginalY=e.getY();
		});
		shape.setOnMouseDragged(e ->{
			double deltaX=e.getX()-cursorOriginalX;
			double deltaY=e.getY()-cursorOriginalY;
			
			edge.setControlX(cursorOriginalX+deltaX);
			edge.setControlY(cursorOriginalY+deltaY);
			
			arrow.updateRotate();
		});
	}

	//GETTERS
	public QuadCurve getEdge() {
		return edge;
	}

	public Text getDisplayValue() {
		return displayValue;
	}

	public String getStartNode() {
		return startNode;
	}

	public String getEndNode() {
		return endNode;
	}

	public Polygon getArrowShape() {
		return arrow.getShape();
	}
	
	
	
	
}
