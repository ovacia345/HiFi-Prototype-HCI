
package nl.ru.ai.Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class MyEllipse implements Drawable, FillColor {
	private double x1, y1, x2, y2;
	public int clickX, clickY;
	private Color fillColor;
	private Color borderColor;
	private int strokeThickness;
	
	public MyEllipse( double x1, double y1, double x2, double y2, Color c ) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		borderColor = c;
		strokeThickness = 7;
	}
	
	@Override
	public void draw(Graphics2D g) {
		Ellipse2D e = new Ellipse2D.Double( getStartX(), getStartY(), getWidth(), getHeight() );
		g.setColor( borderColor );
		g.setStroke( new BasicStroke( strokeThickness ) );
		g.draw( e );
		if( fillColor != null ) {
			g.setColor( fillColor );
			g.fill( e );
		}
	}
	
	@Override
	public boolean contains(int xCor, int yCor) {
		if( y1 == y2 || x1 == x2 ) //Straight line
			return new MyLine( x1, y1, x2, y2, Color.BLACK ).contains( xCor, yCor );
		
		
		double xCenter = x1 + ( ( x2 - x1 ) / 2.0 );
		double yCenter = y1 + ( ( y2 - y1 ) / 2.0 );
		double xRadius = Math.abs( xCenter - x1 );
		double yRadius = Math.abs( yCenter - y1 );
		
		if(Math.abs( y2 - y1 ) == Math.abs( x2 - x1 ) ) //Perfect Circle
			return Math.pow( ( (double) xCor - xCenter ), 2.0 ) 
				+ Math.pow( ( (double) yCor - yCenter ), 2.0 ) <= Math.pow( xRadius, 2.0 );
		
		return ( Math.pow( ( (double) xCor - xCenter ), 2.0 ) / Math.pow( xRadius, 2.0 ) ) 
				+ ( Math.pow( ( (double) yCor - yCenter ), 2.0 ) / Math.pow( yRadius, 2.0 ) ) <= 1.0;
	}
	
	@Override
	public void setShapeSize(int xCor, int yCor) {
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
	public void changeFillColor(Color c) {
		fillColor = c;
	}
	
	@Override
	public void makeBorderThicker() {
		strokeThickness = strokeThickness + 10;
	}
	
	@Override
	public void makeBorderThinner() {
		if(strokeThickness >= 10)
			strokeThickness = strokeThickness - 10;
	}
	
	@Override
	public void shapeClick( int xCor, int yCor ) {
		clickX = xCor;
		clickY = yCor;
	}
	
	@Override
	public void moveShape( int xCor, int yCor ) {
			x1 = x1 + ( xCor - clickX );
			y1 = y1 + ( yCor - clickY );
			x2 = x2 + ( xCor - clickX );
			y2 = y2 + ( yCor - clickY );
			
			clickX = xCor;
			clickY = yCor;
	}
	
	@Override
	public void resetClickValues() {
		clickX = 0;
		clickY = 0;
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
		return new Ellipse2D.Double( getStartX(), getStartY(), getWidth(), getHeight() );
	}
	
	private double getStartX() {
		return Math.min( x1, x2 );
	}
	
	private double getEndX() {
		return Math.max( x1, x2 );
	}
	
	private double getStartY() {
		return Math.min( y1, y2 );
	}
	
	private double getEndY() {
		return Math.max( y1, y2 );
	}
	
	private double getWidth() {
		return Math.abs( x1 - x2 );
	}
	
	private double getHeight() {
		return Math.abs( y1 - y2 );
	}
}
