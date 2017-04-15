package nl.ru.ai.Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	public DrawPanel dp;
	public ShapeButtonPanel sbp;
	public ToolButtonPanel tbp;
	public ColorButtonPanel cbp;
	public StopWatch sw;

	public Window() {
		super();
		setTitle( "Callbacks" );
		setSize( new Dimension( 1280, 720 ) );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		getContentPane().setLayout( new BorderLayout() );

		dp = new DrawPanel( this );

		sbp = new ShapeButtonPanel( this );
		sbp.setLayout( new BoxLayout( sbp, BoxLayout.Y_AXIS ) );

		tbp = new ToolButtonPanel( this );
		
		cbp = new ColorButtonPanel( this );
		cbp.setLayout( new GridLayout( 0, 1 ) );

		getContentPane().add( dp, BorderLayout.CENTER );
		getContentPane().add( sbp, BorderLayout.WEST );
		getContentPane().add( tbp, BorderLayout.NORTH );
		getContentPane().add( cbp, BorderLayout.EAST );

		setVisible( true );
	}
}
