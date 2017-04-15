package nl.ru.ai.Interface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseListener, MouseMotionListener {
	private DrawPanel dp;
	
	public InputHandler( DrawPanel dp ) {
		this.dp = dp;
	}
	
	public void mousePressed( MouseEvent e ) {
		
		switch( dp.getTool() ) {
		case RECTANGLE_TOOL:
		case DIAMOND_TOOL:
		case TRIANGLE_UP_TOOL:
		case TRIANGLE_DOWN_TOOL:
		case ELLIPSE_TOOL:
		case LINE_TOOL:
		case FREE_DRAW_TOOL:
		case SPECIAL_FREE_DRAW_TOOL:
			dp.setShapePosition( e.getX(), e.getY() );
			break;
		case MOVE_TOOL:
		case RESIZE_SHAPE_TOOL:
			dp.shapeClick( e.getX(), e.getY() );
			break;
		case DELETE_TOOL:
			dp.deleteShape( e.getX(), e.getY() );
			break;
		case PAINT_TOOL:
			dp.changeColor( e.getX(), e.getY() );
			break;
		case BORDER_THICKER_TOOL:
			dp.makeBorderThicker( e.getX(), e.getY() );
			break;
		case BORDER_THINNER_TOOL:
			dp.makeBorderThinner( e.getX(), e.getY() );
			break;
		case LAYER_UP_TOOL:
			dp.shapeLayerUp( e.getX(), e.getY() );
			break;
		case LAYER_DOWN_TOOL:
			dp.shapeLayerDown( e.getX(), e.getY() );
			break;
		case TEXT_TOOL:
			dp.addText( e.getX(), e.getY() );
			break;
		case IMAGE_TOOL:
			dp.addImage( e.getX(), e.getY() );
			break;
		}
	}
	
	public void mouseReleased( MouseEvent e ) {
		
		switch( dp.getTool() ) {
		case MOVE_TOOL:
		case RESIZE_SHAPE_TOOL:
			dp.resetClickValues();
			break;
		case FREE_DRAW_TOOL:
			dp.drawFree();
			break;
		case SPECIAL_FREE_DRAW_TOOL:
			dp.drawShape();
			break;
		}
	}
	
	public void mouseDragged( MouseEvent e ) {

		switch ( dp.getTool() ) {
		case RECTANGLE_TOOL:
		case DIAMOND_TOOL:
		case TRIANGLE_UP_TOOL:
		case TRIANGLE_DOWN_TOOL:
		case ELLIPSE_TOOL:
		case LINE_TOOL:
			dp.setShapeSize( e.getX(), e.getY() );
			break;
		case MOVE_TOOL:
			dp.moveShape( e.getX(), e.getY() );
			break;
		case FREE_DRAW_TOOL:
		case SPECIAL_FREE_DRAW_TOOL:
			dp.setShapePosition( e.getX(), e.getY() );
			break;
		case RESIZE_SHAPE_TOOL:
			dp.resizeShape( e.getX(), e.getY() );
		}
	}
	
	public void mouseEntered( MouseEvent e ) {}

	public void mouseMoved(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}
