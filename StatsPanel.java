
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class StatsFrame extends JFrame{
	
	public static final int STATS_HEIGHT = 400;
	public static final int STATS_WIDTH = 400;
	
	public StatsFrame() {
		super();


		// Do note destroy the frame, simply hide it
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		
		// Do not show it right away.
		setVisible(false);
		
		
		JLabel label = new JLabel("");
		

		// Add the JPanel
//		getContentPane().add(view);
		
		

		// Size the frame.
		
//		pack();

	}
	
}
