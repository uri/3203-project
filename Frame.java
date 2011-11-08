import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Frame extends JFrame{

	public static final String FRAME_NAME = "3203 - Networking";
	Network network;
	Panel view;
	
	
	public Frame(String name) {
		super(name);
		

		// Make sure it exists properly
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the JPanel
		view = new Panel();
		getContentPane().add(view);
		
		//Create the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu sensorsMenu = new JMenu("Sensors");
		JMenuItem sampleSensors = new JMenuItem("10");
		sensorsMenu.add(sampleSensors);
		menuBar.add(sensorsMenu);
		
		//action listeners for the menu bar
		sampleSensors.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("debuggin!");
				network = new Network(10);
				view.DrawNetwork(network);
			}
		});
		

		// Size the frame.
		setSize(Panel.WIDTH, Panel.HEIGHT);
//		pack();

		// Show it.
		setVisible(true);
	}
	
	public static void main(String [] args){
		new Frame(FRAME_NAME);
	}
	
}