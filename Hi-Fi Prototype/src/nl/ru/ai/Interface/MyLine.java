package nl.ru.ai.Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class MyLine implements Drawable {
	private double x1, y1, x2, y2;
	public int clickX, clickY;
	private Color color;
	private int strokeThickness;
	
	public MyLine( double x1, double y1, double x2, double y2, Color c ) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		color = c;
		strokeThickness = 5;
	}
	
	public void changeColor( Color c ) {
		color = c;
	}
	
	@Override
	public void draw( Graphics2D g ) {
		Line2D l = new Line2D.Double( x1, y1, x2, y2 );
		g.setColor( color );
		g.setStroke( new BasicStroke( strokeThickness ) );
		g.draw( l );
	}
	
	@Override
	public boolean contains( int xCor, int yCor ) {
		if( x2 < x1 ) {
			double help = x1;
			x1 = x2;
			x2 = help;
			help = y1;
			y1 = y2;
			y2 = help;
		}
		
		double derivative = ( y2 - y1 ) / ( x2 - x1 );
					
		for( double x = x1, y = y1; x <= x2; x = x + 0.1, y = y + 0.1 * derivative ) {
			if( xCor >= (int) x - 5 && xCor <= (int) x + 5 
			&& yCor >= (int) y - 5 && yCor <= (int) y + 5 ) {
				return true;
			}
		}
			
		if(derivative > 100 || derivative < -100) { // Infinite derivative
			return yCor >= (int) Math.min( y1, y2 ) && yCor <= (int) Math.max( y1, y2 )
					&& xCor >= (int) x1 - 5 && xCor <= (int) x1 + 5 ;
		}
		
		return false;
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
	public Color getBorderColor() {
		return color;
	}
	
	@Override
	public void selectShape() {
		color = Color.RED;
		strokeThickness = strokeThickness + 5;
	}
	
	@Override 
	public void unselectShape() {
		color = Color.BLACK;
		strokeThickness = strokeThickness - 5;
	}
	
	@Override
	public Shape toShape() {
		return new Line2D.Double( x1, y1, x2, y2 );
	}
}
