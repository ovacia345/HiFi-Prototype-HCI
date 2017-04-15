package nl.ru.ai.Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public interface Drawable {
	public void draw( Graphics2D g);
	
	public void setShapeSize(int xCor, int yCor);
	
	public void resizeShape( int xCor, int yCor );
	
	public boolean contains(int xCor, int yCor);
	
	public void makeBorderThicker();
	
	public void makeBorderThinner();
	
	public void shapeClick( int xCor, int yCor );
	
	public void moveShape( int xCor, int yCor );
	
	public void resetClickValues();
	
	public int getClickX();
	
	public int getClickY();
	
	public Color getBorderColor();
	
	public void selectShape();
	
	public void unselectShape();
	
	public Shape toShape();
}
