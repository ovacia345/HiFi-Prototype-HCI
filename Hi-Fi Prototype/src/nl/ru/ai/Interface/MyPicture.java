package nl.ru.ai.Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class MyPicture implements Drawable {
	private BufferedImage image;
	private double x, y;
	private DrawPanel dp;
	private int clickX, clickY;
	
	public MyPicture( BufferedImage image, int xCor, int yCor, DrawPanel dp ) {
		this.image = image;
		x = xCor;
		y = yCor;
		this.dp = dp;
	}
	
	public int getClickX() {
		return clickX;
	}
	
	public int getClickY() {
		return clickY;
	}

	@Override
	public void draw( Graphics2D g ) {
		g.drawImage( image, (int) x, (int) y, dp );
	}
	
	@Override
	public boolean contains( int xCor, int yCor ) {
		return x < xCor && getEndX() > xCor && y < yCor && getEndY() > yCor;
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
	public Shape toShape() {
		return new Rectangle2D.Double( x, y, image.getWidth(), image.getHeight() );
	}
	
	@Override
	public void resizeShape( int xCor, int yCor ) {
		double xChangeFromMid = ( xCor - x ) - ( clickX - x );
		double newWidth = image.getWidth() + xChangeFromMid;
		
		double yChangeFromMid = ( yCor - y ) - ( clickY - y );
		double newHeight = image.getHeight() + yChangeFromMid;
		
		Image temporaryImage = image.getScaledInstance( (int) newWidth, (int) newHeight, Image.SCALE_SMOOTH );
		BufferedImage newImage = new BufferedImage( (int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_ARGB );
		
		Graphics2D g = newImage.createGraphics();
		g.drawImage( temporaryImage, 0, 0, null );
		g.dispose();
		
		image = newImage;
		
		clickX = xCor;
		clickY = yCor;
	}
	
	private double getEndX() {
		return x + image.getWidth();
	}
	
	private double getEndY() {
		return y + image.getHeight();
	}

	@Override
	public void selectShape() {}

	@Override
	public void unselectShape() {}
}
