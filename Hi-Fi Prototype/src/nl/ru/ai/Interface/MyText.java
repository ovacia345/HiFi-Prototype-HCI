package nl.ru.ai.Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class MyText implements Drawable {
	private String text;
	private double x, y;
	private int textSize;
	private Color color;
	private int clickX, clickY;
	
	public MyText( String text, int xCor, int yCor, Color c ) {
		this.text = text;
		textSize = 20;
		x = xCor;
		y = yCor;
		this.color = c;
	}
	
	@Override
	public void draw( Graphics2D g ) {
		g.setFont( new Font( "dialog", 1, textSize ) );
		g.setColor( color );
		g.drawString( text, (int) x, (int) y );
	}

	@Override
	public void resizeShape( int xCor, int yCor ) {
		if( Math.abs( xCor - x ) < Math.abs( clickX - x ) - 4.0d ) {
			textSize--;
			clickX = xCor;
			clickY = yCor;
		} else if( Math.abs( xCor - x ) > Math.abs( clickX - x ) + 4.0d ) {
			textSize++;
			clickX = xCor;
			clickY = yCor;
		} 		
	}

	@Override
	public boolean contains( int xCor, int yCor ) {
		return x < xCor && getEndX() > xCor && getStartY() < yCor && getEndY() > yCor;
	}

	@Override
	public void shapeClick( int xCor, int yCor ) {
		clickX = xCor;
		clickY = yCor;
		
	}

	@Override
	public void moveShape( int xCor, int yCor ) {
		x = x + ( xCor - clickX );
		y = y + ( yCor - clickY );
		
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
	public Shape toShape() {
		return new Rectangle2D.Double( x, getStartY(), getWidth(), getHeight() );
	}
	
	public void changeText( String text ) {
		this.text = text;
	}
	
	@Override
	public void selectShape() {}
	
	@Override
	public void unselectShape() {}
	
	private double getEndX() {
		return x + text.length() * textSize / 2.0d;
	}
	
	private double getStartY() {
		return y - textSize * 0.8d;
	}
	
	private double getEndY() {
		return y + textSize * 0.2d;
	}
	
	private double getWidth() {
		return getEndX() - x;
	}
	
	private double getHeight() {
		return getEndY() - getStartY();
	}
}
