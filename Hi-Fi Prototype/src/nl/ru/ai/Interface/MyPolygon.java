package nl.ru.ai.Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class MyPolygon implements Drawable {
	private int[] xArray;
	private int[] yArray;
	private int clickX, clickY;
	private Color color;
	private int strokeThickness;
	
	public MyPolygon( int[] xArray, int[] yArray, Color c ) {
		this.xArray = xArray;
		this.yArray = yArray;
		color = c;
		strokeThickness = 5;
	}
	
	@Override
	public void draw( Graphics2D g ) {
		g.setColor( color );
		g.setStroke( new BasicStroke( strokeThickness ) );
		for( int i = 0; i < xArray.length - 1; i++ ) {
			Line2D l = new Line2D.Double( xArray[ i ], yArray[ i ], xArray[ i + 1 ], yArray[ i + 1 ] ) ;
			g.draw( l );
		}
	}
	
	@Override
	public boolean contains( int xCor, int yCor ) {
		for( int i = 0; i < xArray.length - 1; i++ ) {
			if( new MyLine( xArray[ i ], yArray[ i ], xArray[ i + 1 ], yArray[ i + 1 ], Color.BLACK ).contains( xCor, yCor ) ) {
				return true;
			}
		}
		return false;
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
		for( int i = 0; i < xArray.length; i++ ) {
			xArray[ i ] = xArray[ i ] + ( xCor - clickX );
			yArray[ i ] = yArray[ i ] + ( yCor - clickY );
		}
			
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
		return new Polygon( xArray, yArray, xArray.length );
	}
	
	public void setShapeSize( int i1, int i2 ) {}
}
