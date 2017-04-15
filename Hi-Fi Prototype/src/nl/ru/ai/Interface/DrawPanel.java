package nl.ru.ai.Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private final double PIXELS = 30.0d;
	private final String SOM_PATH = "SelfOrganisingMaps\\large_vectors_1500_750.txt";
	private final String IMAGE_PATH = "DrawImage\\image.bmp";
	
	private InputHandler inputHandler;
	private Window window;
	private ArrayList< Drawable > shapesList;
	private ArrayList< MyPoint > pointList;
	private Tool_t tool;
	private Color selectedColor;
	private SelfOrganisingMap som;

	public DrawPanel( Window window ) {
		super();
		this.window = window;
		
		inputHandler = new InputHandler( this );
		addMouseListener( inputHandler );
		addMouseMotionListener( inputHandler );
		
		shapesList = new ArrayList< Drawable >();
		pointList = new ArrayList< MyPoint >();
		tool = Tool_t.NULL_TOOL;
		selectedColor = Color.BLACK;
		
		som = new SelfOrganisingMap( SOM_PATH );
	}

	public void deleteShape( int xCor, int yCor ) {
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ).contains( xCor, yCor ) ) {
				shapesList.remove( i );
				break;
			}
		}
		
		repaint();
	}

	public void setShapePosition( int xCor, int yCor ) {

		switch ( tool ) {
		case RECTANGLE_TOOL:
			shapesList.add( new MyRectangle( xCor, yCor, xCor, yCor, selectedColor ) );
			break;
		case DIAMOND_TOOL:
			shapesList.add( new MyDiamond( xCor, yCor, xCor, yCor, selectedColor ) );
			break;
		case TRIANGLE_UP_TOOL:
			shapesList.add( new MyTriangleUp( xCor, yCor, xCor, yCor, selectedColor ) );
			break;
		case TRIANGLE_DOWN_TOOL:
			shapesList.add( new MyTriangleDown( xCor, yCor, xCor, yCor, selectedColor ) );
			break;
		case ELLIPSE_TOOL:
			shapesList.add( new MyEllipse( xCor, yCor, xCor, yCor, selectedColor ) );
			break;
		case LINE_TOOL:
			shapesList.add( new MyLine( xCor, yCor, xCor, yCor, selectedColor ) );
			break;
		case FREE_DRAW_TOOL:
		case SPECIAL_FREE_DRAW_TOOL:
			pointList.add( new MyPoint( xCor, yCor ) );
			break;
		}
		
		repaint();
	}

	public void setShapeSize( int xCor, int yCor ) {
		shapesList.get( shapesList.size() - 1 ).setShapeSize( xCor, yCor );
		
		repaint();
	}

	public void setTool( Tool_t tool ) {
		this.tool = tool;
	}

	public Tool_t getTool() {
		return tool;
	}
	
	public void setSelectedColor( Color c ) {
		selectedColor = c;
	}

	public void changeColor( int xCor, int yCor ) {
		
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ) instanceof FillColor && shapesList.get( i ).contains( xCor, yCor ) ) {
				( (FillColor) shapesList.get( i ) ).changeFillColor( selectedColor );
				break;
			}
		}
		
		repaint();
	}

	public void makeBorderThicker( int xCor, int yCor ) {
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ).contains( xCor, yCor ) ) {
				shapesList.get( i ).makeBorderThicker();
				break;
			}
		}
		
		repaint();
	}

	public void makeBorderThinner( int xCor, int yCor ) {
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ).contains( xCor, yCor ) ) {
				shapesList.get( i ).makeBorderThinner();
				break;
			}
		}
		
		repaint();
	}

	public void shapeClick( int xCor, int yCor ) {
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ).contains( xCor, yCor ) ) {
				shapesList.get( i ).shapeClick( xCor, yCor );
				break;
			}
		}
	}

	public void moveShape( int xCor, int yCor ) {
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ).getClickX() != 0 && shapesList.get( i ).getClickY() != 0 ) {
				shapesList.get( i ).moveShape( xCor, yCor );
				break;
			}
		}
		
		repaint();
	}

	public void resetClickValues() {
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			shapesList.get( i ).resetClickValues();
		}
	}

	public void newCanvas() {
		shapesList = new ArrayList< Drawable >();
		
		repaint();
	}

	public void shapeLayerDown( int xCor, int yCor ) {		
		for ( int i = shapesList.size() - 1; i >= 0; i--) {
			if ( shapesList.get( i ).contains( xCor, yCor ) ) {
				Shape shapeI = shapesList.get( i ).toShape();
				for( int a = i - 1; a >= 0; a-- ) {
					Shape shapeA = shapesList.get( a ).toShape();
					
					if( shapeI.getBounds2D().intersects( shapeA.getBounds2D() ) ) {
						Drawable help = shapesList.get( i );
						for( int b = i; b > a; b-- ) {
							shapesList.set( b, shapesList.get( b - 1 ) );
						}
						shapesList.set( a, help );
						
						break;
					}
				}
				break;
			}
		}
		
		repaint();
	}
	
	public void shapeLayerUp( int xCor, int yCor ) {		
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ).contains( xCor, yCor ) ) {
				Shape shapeI = shapesList.get( i ).toShape();
				for( int a = i + 1; a < shapesList.size(); a++ ) {
					Shape shapeA = shapesList.get( a ).toShape();
					
					if( shapeI.getBounds2D().intersects( shapeA.getBounds2D() ) ) {
						Drawable help = shapesList.get( i );
						for( int b = i; b < a; b++ ) {
							shapesList.set( b, shapesList.get( b + 1 ) );
						}
						shapesList.set( a, help );
						
						break;
					}
				}
				break;
			}
		}
		
		repaint();
	}
	
	public void drawFree() {
		int[] xArray = new int[ pointList.size() ];
		int[] yArray = new int[ pointList.size() ];
		
		for( int i = 0; i < pointList.size(); i++ ) {
			xArray[ i ] = (int) pointList.get( i ).getX();
			yArray[ i ] = (int) pointList.get( i ).getY();
		}
		
		shapesList.add( new MyPolygon( xArray, yArray, selectedColor ) );
		
		pointList = new ArrayList< MyPoint >();
		
		repaint();
	}

	public void drawShape() {
		
		if( pointList.size() > 1 ) {
			
			pointList = addIntermediatePoints();

			double maxX = 0.0d;
			double minX = window.getBounds().getSize().width;
			double maxY = 0.0d;
			double minY = window.getBounds().getSize().height;

			for ( MyPoint p : pointList ) {
				if ( p.getX() > maxX )
					maxX = p.getX();
				if ( p.getX() < minX )
					minX = p.getX();
				if ( p.getY() > maxY )
					maxY = p.getY();
				if ( p.getY() < minY )
					minY = p.getY();
			}

			normalizePoints( minX, maxX, minY, maxY );
			
			boolean isLine = makeBufferedImage( pointList );
			boolean small = maxX - minX < 29.0d && maxY - minY < 29.0d;
					

			Polygon_t figureType = som.getClassification( TrainSOM.readVectorPublic( IMAGE_PATH ) );// Look at TrainSOM!!
			
			if( isLine && !small ) {
				if( pointList.get( 0 ).getX() < pointList.get( pointList.size() - 1 ).getX() ) {
					if( pointList.get( 0 ).getY() < pointList.get( pointList.size() - 1 ).getY() ) {
						shapesList.add( new MyLine( minX, minY, maxX, maxY, selectedColor ) );
					} else {
						shapesList.add( new MyLine( minX, maxY, maxX, minY, selectedColor ) );
					}
				} else {
					if( pointList.get( 0 ).getY() < pointList.get( pointList.size() - 1 ).getY() ) {
						shapesList.add( new MyLine( minX, maxY, maxX, minY, selectedColor ) );
					} else {
						shapesList.add( new MyLine( minX, minY, maxX, maxY, selectedColor ) );
					}
				}
			} else {
				switch ( figureType ) {
				case SQUARE:
					shapesList.add( new MyRectangle( minX, minY, maxX, maxY, selectedColor ) );
					break;
				case DIAMOND:
					shapesList.add( new MyDiamond( minX, minY, maxX, maxY, selectedColor ) );
					break;
				case UP_TRIANGLE:
					shapesList.add( new MyTriangleUp( minX, minY, maxX, maxY, selectedColor ) );
					break;
				case DOWN_TRIANGLE:
					shapesList.add( new MyTriangleDown( minX, minY, maxX, maxY, selectedColor ) );
					break;
				case CIRCLE:
					shapesList.add( new MyEllipse( minX, minY, maxX, maxY, selectedColor ) );
					break;
				}
			}
		}
			pointList = new ArrayList< MyPoint >();
			
			repaint();
	}

	private ArrayList< MyPoint > addIntermediatePoints() {
		ArrayList<  MyPoint > newPointList = new ArrayList< MyPoint >();

		for ( int i = 0; i < pointList.size() - 1; i++ ) {
			double x1 = pointList.get( i ).getX();
			double y1 = pointList.get( i ).getY();
			double x2 = pointList.get( i + 1 ).getX();
			double y2 = pointList.get( i + 1 ).getY();

			if ( x2 < x1 ) {
				double help = x1;
				x1 = x2;
				x2 = help;
				help = y1;
				y1 = y2;
				y2 = help;
			}

			double derivative = ( y2 - y1 ) / ( x2 - x1 );

			if ( derivative < 100.0d && derivative > -100.0d ) {
				for ( double x = x1, y = y1; x <= x2; x = x + 0.1d, y = y + 0.1d * derivative ) {
					newPointList.add( new MyPoint( (int) x, (int) y ) );
				}

			} else {
				for ( double y = Math.min( y1, y2 ); y <= Math.max( y1, y2 ); y = y + 1.0d ) {
					newPointList.add( new MyPoint( (int) x1, (int) y ) );
				}
			}
		}
		
		return newPointList;
	}

	private void normalizePoints( double minX, double maxX, double minY, double maxY ) {

		double shapeWidth = maxX - minX;
		double shapeHeight = maxY - minY;

		double xScaler = 1.0d;
		double yScaler = 1.0d;

		if ( shapeWidth >= PIXELS ) {
			xScaler = ( PIXELS - 1.0d ) / shapeWidth;
		}

		if ( shapeHeight >= PIXELS ) {
			yScaler = ( PIXELS - 1.0d ) / shapeHeight;
		}

		for ( MyPoint p : pointList ) {
			p.setX( (int) ( ( ( p.getX() - minX ) * xScaler ) ) );
			p.setY( (int) ( ( ( p.getY() - minY ) * yScaler ) ) );
		}
	}
	
	private boolean makeBufferedImage( ArrayList< MyPoint > pointList ) {
		BufferedImage image = map( (int) PIXELS, (int) PIXELS, pointList );
		
		int counter = 0;
		
		savePNG( image, IMAGE_PATH );
		
		for( int x = 0; x < image.getWidth(); x++ ) {
			for( int y = 0; y < image.getHeight(); y++ ) {
				if( new Color( image.getRGB( x, y ) ).getRed() == 0 ) {
					counter++;
				}
			}
		}
				
		return counter < 100;
	}
	
	private static BufferedImage map( int sizeX, int sizeY , ArrayList<MyPoint> pointList ) {
		BufferedImage image = new BufferedImage( sizeX, sizeY, BufferedImage.TYPE_INT_RGB );
		for( int x = 0; x < sizeX; x++) {
			for( int y = 0; y < sizeY; y++) {
				image.setRGB( x, y, Color.WHITE.getRGB() );
			}
		}
		
		for( MyPoint p : pointList ) {
			image.setRGB( (int) p.getX(), (int) p.getY(), Color.BLACK.getRGB() );
		}
		
		return image;
	}
	
	private static void savePNG( BufferedImage image, String path) {
		try {
			RenderedImage renderedImage = image;
			ImageIO.write( renderedImage, "bmp", new File( path ) );
		} catch( IOException e) {
			e.printStackTrace();
		}
	}

	public void addImage( int xCor, int yCor ) {		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory( new File( "." ) );
		chooser.setDialogTitle( "Choose Image!" );
		chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
		chooser.setAcceptAllFileFilterUsed( false );
		
		if( chooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
			try {
				String imagePath = chooser.getSelectedFile().getCanonicalPath();
				shapesList.add( new MyPicture( ImageIO.read( new File( imagePath ) ), xCor, yCor, this ) );
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}
		
		repaint();
	}
	
	public void resizeShape( int xCor, int yCor ) {
		for( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if( shapesList.get( i ).getClickX() != 0 && shapesList.get( i ).getClickY() != 0 ) {
				shapesList.get( i ).resizeShape( xCor, yCor );
				break;
			}
		}
		
		repaint();
	}
	
	public void addText( int xCor, int yCor ) {
		boolean changedText = false;
		
		for ( int i = shapesList.size() - 1; i >= 0; i-- ) {
			if ( shapesList.get( i ) instanceof MyText && shapesList.get( i ).contains( xCor, yCor ) ) {
				String text = JOptionPane.showInputDialog( "New Message:" );
				( (MyText) shapesList.get( i ) ).changeText( text );
				changedText = true;
				break;
			}
		}
		
		if( !changedText ) {
			String text = JOptionPane.showInputDialog( "Input Message:" );
			shapesList.add( new MyText( text, xCor, yCor, selectedColor ) );
		}
		
		repaint();
	}
	
	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		for ( Drawable s : shapesList ) {
			s.draw( (Graphics2D) g );
		}

		for ( int i = 0; i < pointList.size() - 1; i++ ) {
			MyLine l = new MyLine( pointList.get( i ).getX(), pointList.get( i ).getY(), pointList.get( i + 1 ).getX(), pointList.get( i + 1 ).getY(), selectedColor );
			l.draw( (Graphics2D) g );
		}
	}
}
