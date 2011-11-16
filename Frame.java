import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame{

	public static final String FRAME_NAME = "3203 - Networking";
	Network network;
	View view;
	int strength,sensors;

	public final static int WIDTH = 800;
	public final static int HEIGHT = 580;
	
	
	public Frame(String name) {
		super(name);
		strength = 50;
		sensors = 10;

		// Make sure it exists properly
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the JPanel
		view = new View();
		getContentPane().add(view);
		
		//add ActionListeners
		view.GetUpdateButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sensors = view.getSensors();
				strength = view.getStrength();
				update();
			}
		});
		

		// Size the frame.
		setSize(WIDTH, HEIGHT);
//		pack();

		// Show it.
		setVisible(true);
	}
	
	private void update(){
		network = new Network(sensors, strength);
		view.updateNetwork(network, network);
	}
	
	public static void main(String [] args){
		new Frame(FRAME_NAME);
	}
	
}