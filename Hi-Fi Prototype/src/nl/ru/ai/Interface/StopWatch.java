package nl.ru.ai.Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StopWatch extends JPanel {
	private ActionListener inputHandler;
	private JButton button;
	private int time;
	private Timer timer;
	
	public StopWatch() {
		super();
		 
		inputHandler = new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				if( time == 0 ) {
					startStopWatch();
				} else {
					resetStopWatch();
				}
			}
		};		
		
		ImageIcon stopWatch = new ImageIcon( "Icons\\Timer.png" );
		button = new JButton( stopWatch );
		button.addActionListener( inputHandler );
		add( button );
		
		time = 0;
		
		timer = new Timer( 100, new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				updateStopWatch();
			}
		});
	}
	
	public void startStopWatch() {
		button.setText( 0.0 + " seconds" );
		timer.start();
	}
	
	public void updateStopWatch() {
		time++;
		button.setText( (double) time / 10.0d + " seconds" );
	}
	
	public void resetStopWatch() {
		timer.stop();
		time = 0;
	}
}
