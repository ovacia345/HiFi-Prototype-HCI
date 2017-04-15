package nl.ru.ai.Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ShapeButtonPanel extends JPanel {
	private ActionListener inputHandler;
	private JButton[] buttons;

	public ShapeButtonPanel(Window window) {
		super();
		buttons = new JButton[ 10 ];
		inputHandler = new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				window.tbp.unselectButton();
				unselectButton();
				
				switch( e.getActionCommand() ) {
				case "New Rectangle":
					buttons[ 0 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.RECTANGLE_TOOL );
					break;
				case "New Diamond":
					buttons[ 1 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.DIAMOND_TOOL );
					break;
				case "New Triangle Up":
					buttons[ 2 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.TRIANGLE_UP_TOOL );
					break;
				case "New Triangle Down":
					buttons[ 3 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.TRIANGLE_DOWN_TOOL );
					break;
				case "New Ellipse":
					buttons[ 4 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.ELLIPSE_TOOL );
					break;
				case "New Line":
					buttons[ 5 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.LINE_TOOL );
					break;
				case "Free Draw":
					buttons[ 6 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.FREE_DRAW_TOOL );
					break;
				case "Special Free Draw":
					buttons[ 7 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.SPECIAL_FREE_DRAW_TOOL );
					break;
				case "New Text":
					buttons[ 8 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.TEXT_TOOL );
					break;
				case "Import Image":
					buttons[ 9 ].setBackground( new Color( 70, 130, 180 ) );
					window.dp.setTool( Tool_t.IMAGE_TOOL );
					break;
				}
			}
		};
		
		ImageIcon rect = new ImageIcon("Icons\\Rectangle.png");
		buttons[ 0 ] = new JButton( rect );
		buttons[ 0 ].addActionListener( inputHandler );
		buttons[ 0 ].setActionCommand( "New Rectangle" );
		add( buttons[ 0 ] );
		
		ImageIcon diamond = new ImageIcon("Icons\\Diamond.png");
		buttons[ 1 ] = new JButton(diamond);
		buttons[ 1 ].addActionListener( inputHandler );
		buttons[ 1 ].setActionCommand( "New Diamond" );
		add( buttons[ 1 ] );
		
		ImageIcon triangleUp = new ImageIcon("Icons\\TriangleUp.png");
		buttons[ 2 ] = new JButton(triangleUp);
		buttons[ 2 ].addActionListener( inputHandler );
		buttons[ 2 ].setActionCommand( "New Triangle Up" );
		add( buttons[ 2 ] );
		
		ImageIcon triangleDown = new ImageIcon("Icons\\TriangleDown.png");
		buttons[ 3 ] = new JButton(triangleDown);
		buttons[ 3 ].addActionListener( inputHandler );
		buttons[ 3 ].setActionCommand( "New Triangle Down" );
		add( buttons[ 3 ] );
		
		ImageIcon circle = new ImageIcon("Icons\\Circle.png");
		buttons[ 4 ] = new JButton( circle);
		buttons[ 4 ].addActionListener( inputHandler );
		buttons[ 4 ].setActionCommand( "New Ellipse" );
		add( buttons[ 4 ] );
		
		ImageIcon line = new ImageIcon("Icons\\Line.png");
		buttons[ 5 ] = new JButton( line);
		buttons[ 5 ].addActionListener( inputHandler );
		buttons[ 5 ].setActionCommand( "New Line" );
		add( buttons[ 5 ] );
		
		ImageIcon pencil = new ImageIcon( "Icons\\Pencil.png" );
		buttons[ 6 ] = new JButton( pencil );
		buttons[ 6 ].addActionListener( inputHandler );
		buttons[ 6 ].setActionCommand( "Free Draw" );
		add( buttons[ 6 ] );

		ImageIcon pencilStar = new ImageIcon( "Icons\\PencilStar.png" );
		buttons[ 7 ] = new JButton( pencilStar );
		buttons[ 7 ].addActionListener( inputHandler );
		buttons[ 7 ].setActionCommand( "Special Free Draw" );
		add( buttons[ 7 ] );
		
		ImageIcon text = new ImageIcon( "Icons\\Text.png" );
		buttons[ 8 ] = new JButton( text );
		buttons[ 8 ].addActionListener( inputHandler );
		buttons[ 8 ].setActionCommand( "New Text" );
		add( buttons[ 8 ] );
		
		ImageIcon photo = new ImageIcon( "Icons\\Camera.png" );
		buttons[ 9 ] = new JButton( photo );
		buttons[ 9 ].addActionListener( inputHandler );
		buttons[ 9 ].setActionCommand( "Import Image" );
		add( buttons[ 9 ] );
		
	}
	
	public void unselectButton() {
		for( JButton b : buttons ) {
			b.setBackground( null );
		}
	}
}
