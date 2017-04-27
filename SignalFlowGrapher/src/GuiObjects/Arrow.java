package GuiObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

public class Arrow {
	
	
	//01_ATTRIBUTES
	//*************************************************************
	private Polygon shape=new Polygon();
	private QuadCurve curve;
	private Line test=new Line();
	
	
	//02_CONSTRUCTOR
	//*************************************************************
	public Arrow(QuadCurve curve){
		this.curve=curve;
		//********************************
		double p1x=-8; double p1y=0;
		double p2x=-25; double p2y=-7;
		double p3x=-25; double p3y=7;
		Double[] arrowShape = new Double[] {p1x, p1y, p2x, p2y, p3x, p3y};
		shape.getPoints().addAll(arrowShape);
		shape.setFill(Color.BLUE);
		//********************************
		test.startXProperty().bind(curve.controlXProperty());
		test.startYProperty().bind(curve.controlYProperty());
		test.endXProperty().bind(curve.endXProperty());
		test.endYProperty().bind(curve.endYProperty());
		//********************************
		updateTranslate();
		//********************************
		//03_rotation
		double deltaY=curve.getEndY()-curve.getControlY();
		double deltaX=curve.getEndX()-curve.getControlX();
		double angle=Math.atan2(deltaY, deltaX);
		angle=angle*(180/Math.PI);
		shape.setRotate(angle);

	}

	//03_METHODS
	//*************************************************************
	public Polygon getShape() {
		return shape;
	}
	
	//*************************************************************
	public void updateRotate(){
		updateTranslate();
		
		double deltaY=curve.getEndY()-curve.getControlY();
		double deltaX=curve.getEndX()-curve.getControlX();
		double angle=Math.atan2(deltaY, deltaX);
		angle=angle*(180/Math.PI);

		shape.setRotate(angle);
	}
	
	//*************************************************************
	private void updateTranslate(){
		
		double deltaXX=test.getEndX()-test.getStartX();
		double deltaYY=test.getEndY()-test.getStartY();
		double len=Math.sqrt(deltaXX*deltaXX+deltaYY*deltaYY);
		
		double newLen=0.1*len;
		double newX=test.getEndX()-deltaXX*(newLen/len);
		double newY=test.getEndY()-deltaYY*(newLen/len);
		
		shape.setTranslateX(newX+15);
		shape.setTranslateY(newY);
	}
	
	
	

}
