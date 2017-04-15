package nl.ru.ai.Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ToolButtonPanel extends JPanel {
	private ActionListener inputHandler;
	private JButton[] buttons;

	public ToolButtonPanel( Window window ) {
		super();
		buttons = new JButton[ 9 ];
		inputHandler = new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				
				window.sbp.unselectButton();
				unselectButton();
				
				switch( e.getActionCommand() ) {
				case "New Canvas":
					window.dp.newCanvas();
					break;
				case "Move":
					buttons[ 1 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.MOVE_TOOL );
					break;
				case "Delete":
					buttons[ 2 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.DELETE_TOOL );
					break;
				case "Change Color":
					buttons [ 3 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.PAINT_TOOL );
					break;
				case "Make Border Thicker":
					buttons[ 4 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.BORDER_THICKER_TOOL );
					break;
				case "Make Border Thinner":
					buttons[ 5 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.BORDER_THINNER_TOOL );
					break;
				case "Layer Up":
					buttons[ 6 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.LAYER_UP_TOOL );
					break;
				case "Layer Down":
					buttons[ 7 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.LAYER_DOWN_TOOL );
					break;
				case "Resize Shape":
					buttons[ 8 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.RESIZE_SHAPE_TOOL );
					break;
				}
			}
		};

		ImageIcon canvas = new ImageIcon( "Icons\\Canvas.png" );
		buttons[ 0 ] = new JButton( canvas );
		buttons[ 0 ].addActionListener( inputHandler );
		buttons[ 0 ].setActionCommand( "New Canvas" );
		add( buttons[ 0 ] );

		ImageIcon move = new ImageIcon( "Icons\\MoveIcon.png" );
		buttons[ 1 ] = new JButton( move );
		buttons[ 1 ].addActionListener( inputHandler );
		buttons[ 1 ].setActionCommand( "Move" );
		add( buttons[ 1 ] );

		ImageIcon eraser = new ImageIcon( "Icons\\Eraser.png" );
		buttons[ 2 ] = new JButton( eraser );
		buttons[ 2 ].addActionListener( inputHandler );
		buttons[ 2 ].setActionCommand( "Delete" );
		add( buttons[ 2 ] );

		ImageIcon bucket = new ImageIcon( "Icons\\Bucket.png" );
		buttons[ 3 ] = new JButton( bucket );
		buttons[ 3 ].addActionListener( inputHandler );
		buttons[ 3 ].setActionCommand( "Change Color" );
		add( buttons[ 3 ] );

		ImageIcon thick = new ImageIcon( "Icons\\MakeBorderThicker.png" );
		buttons[ 4 ] = new JButton( thick );
		buttons[ 4 ].addActionListener( inputHandler );
		buttons[ 4 ].setActionCommand( "Make Border Thicker" );
		add( buttons[ 4 ] );

		ImageIcon thin = new ImageIcon( "Icons\\MakeBorderThinner.png" );
		buttons[ 5 ] = new JButton( thin );
		buttons[ 5 ].addActionListener( inputHandler );
		buttons[ 5 ].setActionCommand( "Make Border Thinner" );
		add( buttons[ 5 ] );

		ImageIcon layerUp = new ImageIcon( "Icons\\LayerUp.png" );
		buttons[ 6 ] = new JButton( layerUp );
		buttons[ 6 ].addActionListener( inputHandler );
		buttons[ 6 ].setActionCommand( "Layer Up" );
		add( buttons[ 6 ] );
		
		ImageIcon layerDown = new ImageIcon( "Icons\\LayerDown.png" );
		buttons[ 7 ] = new JButton( layerDown );
		buttons[ 7 ].addActionListener( inputHandler );
		buttons[ 7 ].setActionCommand( "Layer Down" );
		add( buttons[ 7 ] );
		
		ImageIcon resizeShape = new ImageIcon( "Icons\\ResizeShape.png" );
		buttons[ 8 ] = new JButton( resizeShape );
		buttons[ 8 ].addActionListener( inputHandler );
		buttons[ 8 ].setActionCommand( "Resize Shape" );
		add( buttons[ 8 ] );
		
		StopWatch sw = new StopWatch();
		add( sw );
	}
	
	public void unselectButton() {
		for( JButton b : buttons ) {
			b.setBackground( null );
		}
	}
}
