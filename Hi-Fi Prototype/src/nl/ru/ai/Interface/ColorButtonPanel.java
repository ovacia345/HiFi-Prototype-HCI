package nl.ru.ai.Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorButtonPanel extends JPanel{
	public ActionListener inputHandler;
	private JButton[] buttons;
	private JLabel sampleColor;
	
	public ColorButtonPanel( Window window ) {
		super();
		buttons = new JButton[ 14 ];
		
		inputHandler = new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				
				switch( e.getActionCommand() ) {
				case "Magenta":
					sampleColor.setForeground( Color.MAGENTA );
					window.dp.setSelectedColor( Color.MAGENTA );
					break;
				case "Blue":
					sampleColor.setForeground( Color.BLUE );
					window.dp.setSelectedColor( Color.BLUE );
					break;
				case "Cyan":
					sampleColor.setForeground( Color.CYAN );
					window.dp.setSelectedColor( Color.CYAN );
					break;
				case "Green":
					sampleColor.setForeground( Color.GREEN );
					window.dp.setSelectedColor( Color.GREEN );
					break;
				case "Yellow":
					sampleColor.setForeground( Color.YELLOW );
					window.dp.setSelectedColor( Color.YELLOW );
					break;
				case "Orange":
					sampleColor.setForeground( Color.ORANGE );
					window.dp.setSelectedColor( Color.ORANGE );
					break;
				case "Red":
					sampleColor.setForeground( Color.RED );
					window.dp.setSelectedColor( Color.RED );
					break;
				case "Pink":
					sampleColor.setForeground( Color.PINK );
					window.dp.setSelectedColor( Color.PINK );
					break;
				case "Black":
					sampleColor.setForeground( Color.BLACK );
					window.dp.setSelectedColor( Color.BLACK );
					break;
				case "Dark Gray":
					sampleColor.setForeground( Color.DARK_GRAY );
					window.dp.setSelectedColor( Color.DARK_GRAY );
					break;
				case "Gray":
					sampleColor.setForeground( Color.GRAY );
					window.dp.setSelectedColor( Color.GRAY );
					break;
				case "Light Gray":
					sampleColor.setForeground( Color.LIGHT_GRAY );
					window.dp.setSelectedColor( Color.LIGHT_GRAY );
					break;
				case "White":
					sampleColor.setForeground( Color.WHITE );
					window.dp.setSelectedColor( Color.WHITE );
					break;
				case "More ->":
					buttons[ 13 ].setBackground( new Color( 70, 130, 180 ) );
					
					Color color;
					if( ( color = JColorChooser.showDialog( null, "Choose a color.", sampleColor.getForeground() ) ) != null ) {
						sampleColor.setForeground( color );
						window.dp.setSelectedColor( color );
					}
					
					buttons[ 13 ].setBackground( null );
					break;
				}
			}
		};
		
		sampleColor = new JLabel( "Color" );
		sampleColor.setFont( new Font( "dialog", 1, 33 ) );
		sampleColor.setForeground( Color.BLACK );
		add( sampleColor );
		
		buttons[ 0 ] = new JButton( "Magenta" );
		buttons[ 0 ].addActionListener( inputHandler );
		buttons[ 0 ].setBackground( Color.MAGENTA );
		add( buttons[ 0 ] );	
		
		buttons[ 1 ] = new JButton( "Blue" );
		buttons[ 1 ].addActionListener( inputHandler );
		buttons[ 1 ].setBackground( Color.BLUE );
		add( buttons[ 1 ] );
		
		buttons[ 2 ] = new JButton( "Cyan" );
		buttons[ 2 ].addActionListener( inputHandler );
		buttons[ 2 ].setBackground( Color.CYAN );
		add( buttons[ 2 ] );
		
	
		buttons[ 3 ] = new JButton( "Green" );
		buttons[ 3 ].addActionListener( inputHandler );
		buttons[ 3 ].setBackground( Color.GREEN );
		add( buttons[ 3 ] );
		
		buttons[ 4 ] = new JButton( "Yellow" );
		buttons[ 4 ].addActionListener( inputHandler );
		buttons[ 4 ].setBackground( Color.YELLOW );
		add( buttons[ 4 ] );
		
		buttons[ 5 ] = new JButton( "Orange" );
		buttons[ 5 ].addActionListener( inputHandler );
		buttons[ 5 ].setBackground( Color.ORANGE );
		add( buttons[ 5 ] );
		
		buttons[ 6 ] = new JButton( "Red" );
		buttons[ 6 ].addActionListener( inputHandler );
		buttons[ 6 ].setBackground( Color.RED );
		add( buttons[ 6 ] );
		
		buttons[ 7 ] = new JButton( "Pink" );
		buttons[ 7 ].addActionListener( inputHandler );
		buttons[ 7 ].setBackground( Color.PINK );
		add( buttons[ 7 ] );
		
		buttons[ 8 ] = new JButton( "Black" );
		buttons[ 8 ].addActionListener( inputHandler );
		buttons[ 8 ].setBackground( Color.BLACK );
		add( buttons[ 8 ] );
		
		buttons[ 9 ] = new JButton( "Dark Gray" );
		buttons[ 9 ].addActionListener( inputHandler );
		buttons[ 9 ].setBackground( Color.DARK_GRAY );
		add( buttons[ 9 ] );		
		
		buttons[ 10 ] = new JButton( "Gray" );
		buttons[ 10 ].addActionListener( inputHandler );
		buttons[ 10 ].setBackground( Color.GRAY );
		add( buttons[ 10 ] );
		
		buttons[ 11 ] = new JButton( "Light Gray" );
		buttons[ 11 ].addActionListener( inputHandler );
		buttons[ 11 ].setBackground( Color.LIGHT_GRAY );
		add( buttons[ 11 ] );
		
		buttons[ 12 ] = new JButton( "White" );
		buttons[ 12 ].addActionListener( inputHandler );
		buttons[ 12 ].setBackground( Color.WHITE );
		add( buttons[ 12 ] );
		
		buttons[ 13 ] = new JButton( "More ->" );
		buttons[ 13 ].addActionListener( inputHandler );
		add( buttons[ 13 ] );
	}
}