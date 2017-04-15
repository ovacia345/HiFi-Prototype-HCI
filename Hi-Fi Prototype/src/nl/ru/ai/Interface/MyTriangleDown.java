package nl.ru.ai.Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class MyTriangleDown implements Drawable, FillColor {
	private double x1, y1, x2, y2;
	private int clickX, clickY;
	private Color fillColor;
	private Color borderColor;
	private int strokeThickness;
	
	public MyTriangleDown( double x1, double y1, double x2, double y2, Color c ) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		strokeThickness = 7;
		borderColor = c;
	}
	
	@Override
	public void draw(Graphics2D g) {
		Polygon p = new Polygon( xArray(), yArray(), 3 );
		g.setColor( borderColor );
		g.setStroke( new BasicStroke( strokeThickness ) );
		g.drawPolygon( p );
		if( fillColor != null ) {
			g.setColor( fillColor );
			g.fillPolygon( p );
		}
	}
	
	@Override
	public boolean contains( int xCor, int yCor ) {
		double firstEdge = yArray()[0] + ( ( (double) yArray()[1] - (double) yArray()[0] ) / ( (double) xArray()[1] - (double) xArray()[0] ) ) * ( (double) xCor - (double) xArray()[0] );
		double secondEdge = yArray()[1] + ( ( (double) yArray()[2] - (double) yArray()[1] ) / ( (double) xArray()[2] - (double) xArray()[1] ) ) * ( (double) xCor - (double) xArray()[1] );
		double thirdEdge = yArray()[0] + ( ( (double) yArray()[2] - (double) yArray()[0] ) / ( (double) xArray()[2] - (double) xArray()[0] ) ) * ( (double) xCor - (double) xArray()[0] );
		
		return yCor < firstEdge && yCor < secondEdge && yCor > thirdEdge;
	}
	
	@Override
	public void changeFillColor(Color c) {
		fillColor = c;
	}
	
	@Override
	public void setShapeSize( int xCor, int yCor ) {
		x2 = xCor;
		y2 = yCor;
	}
	
	@Override
	public void resizeShape( int xCor, int yCor ) {
		x2 = x2 + ( xCor - clickX );
		y2 = y2 + ( yCor - clickY );
		
		clickX = xCor;
		clickY = yCor;
	}
	
	@Override
	public int getClickX() {
		return clickX;
	}
	
	@Override
	public int getClickY() {
		return clickY;
	}
	
	@Override
	public void shapeClick( int xCor, int yCor ) {
		clickX = xCor;
		clickY = yCor;
	}
	
	@Override
	public void moveShape( int xCor, int yCor ) {
		x1 = x1 + ( xCor - clickX);
		y1 = y1 + ( yCor - clickY);
		x2 = x2 + ( xCor - clickX);
		y2 = y2 + ( yCor - clickY);
		
		clickX = xCor;
		clickY = yCor;
	}
	
	@Override
	public void resetClickValues() {
		clickX = 0;
		clickY = 0;
	}
	
	@Override
	public void makeBorderThicker() {
		strokeThickness = strokeThickness + 10;
	}
	
	@Override
	public void makeBorderThinner() {
		if(strokeThickness >= 10 ) {
			strokeThickness = strokeThickness - 10;
		}
	}
	
	@Override
	public Color getFillColor() {
		return fillColor;
	}
	
	@Override
	public Color getBorderColor() {
		return borderColor;
	}
	
	@Override
	public void selectShape() {
		borderColor = Color.RED;
		strokeThickness = strokeThickness + 5;
	}
	
	@Override 
	public void unselectShape() {
		borderColor = Color.BLACK;
		strokeThickness = strokeThickness - 5;
	}
	
	@Override
	public Shape toShape() {
		return new Polygon( xArray(), yArray(), 3 );
	}
	
	public int[] xArray() {
		int[] xArray = new int[3];
		xArray[0] = Math.min( (int) x1, (int) x2 );
		xArray[1] = ( (int) x1 + (int) x2 ) / 2;
		xArray[2] = Math.max( (int) x1, (int) x2 );
		
		return xArray;
	}
	
	public int[] yArray() {
		int[] yArray = new int[3];
		yArray[0] = Math.min( (int) y1, (int) y2 );
		yArray[1] = Math.max( (int) y1, (int) y2 );
		yArray[2] = Math.min( (int) y1, (int) y2 );
		
		return yArray;
	}
}
