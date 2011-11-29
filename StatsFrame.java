/*************************************************************************************
 * ===================================================================================
 * 
 * This is the frame that pops up when the user hits the Stats button.
 * It displays useful information about the program. 
 * 
 * Uses StatisticsRunner
 * 
 * ===================================================================================
 *************************************************************************************/
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class StatsFrame extends JFrame{
	
	public static final int STATS_HEIGHT = 440;
	public static final int STATS_WIDTH = 305;
	
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 40;
	
	StatisticsRunner stats;
	
	JSlider slider;
	
	Container contentPane;
	
	JLabel 	numNodes;
	JLabel  rtShortestPath;
	JLabel  rtDiameter;
	JLabel  rtHops;
	JLabel  angle;
	JLabel  strength;
	JLabel 	numNetworksLabel;
	JLabel 	totalNumberNodes;
	
	int numNetworks;
	
	/**
	 * 
	 */
	public StatsFrame() {
		super("Statistics");
		setLayout(null);
		
		contentPane = getContentPane();
		
		// Network numbers
		

		// Size
		setSize(STATS_WIDTH, STATS_HEIGHT);
		
		// Do note destroy the frame, simply hide it
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Do not show it right away.
		setVisible(false);
		
		JLabel instructions = new JLabel("Move the slider to select the number of networks.");
		instructions.setSize(STATS_WIDTH, 50);
		instructions.setLocation(5, 5);
		getContentPane().add(instructions);
		
		instructions = new JLabel("Hit Go!");
		instructions.setSize(STATS_WIDTH, 50);
		instructions.setLocation(5, 18);
		getContentPane().add(instructions);
		
		
		addLabel("Num Nodes", 0*LABEL_WIDTH , 1 * LABEL_HEIGHT );
		
		addLabel("Shortest Path T", 0*LABEL_WIDTH , 2 * LABEL_HEIGHT );
		addLabel("Diamter T", 0*LABEL_WIDTH , 3 * LABEL_HEIGHT );
		addLabel("Hops T", 0*LABEL_WIDTH , 4 * LABEL_HEIGHT );
		
		addLabel("Strength", 0*LABEL_WIDTH , 5 * LABEL_HEIGHT );
		addLabel("Num Networks", 0*LABEL_WIDTH , 6 * LABEL_HEIGHT );
		
		addLabel("Strength", 0*LABEL_WIDTH , 5 * LABEL_HEIGHT );
		addLabel("Total Nodes", 0*LABEL_WIDTH , 7 * LABEL_HEIGHT );
		
		initDisplayLabels();
		
		slider = new JSlider(SwingConstants.HORIZONTAL, 5,100, 30);
		slider.setLocation(5, STATS_HEIGHT - 100);
		slider.setSize(STATS_WIDTH - 100, 50);
		slider.setVisible(true);
		//Turn on labels at major tick marks.
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
			
		getContentPane().add(slider);
		
	
		// Add the listener
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				handleSlider(source);
				numNetworksLabel.setText(""+numNetworks);
			}
		});
		
		// Get the statistics class
		handleSlider(slider);

	}
	
	private void handleSlider(JSlider slider) {
	    if (!slider.getValueIsAdjusting()) {
	    	numNetworks = (int)slider.getValue();
	    }
	}
	
	public void update() {
		if (stats == null) return;
		
		handleSlider(slider);
		
		numNodes.setText(""+(int)stats.getNumNodes());
		rtShortestPath.setText(""+(int)stats.getRtShortestPath());
		rtDiameter.setText(""+(int)stats.getRtDiameter());
		rtHops.setText(""+(int)stats.getRtHops());
		strength.setText(""+(int)stats.getStrength());
		numNetworksLabel.setText(""+(int)stats.getNumNetworks());
		totalNumberNodes.setText(""+ (int)(stats.getNumNodes() * stats.getNumNetworks()));
	}
	

	private void addLabel(String name, int x, int y) {
		JLabel label = new JLabel(name);
		addLabel(label, x, y);
	}
	
	private void addLabel(JLabel label, int x, int y) {
		label.setSize(LABEL_WIDTH,LABEL_HEIGHT);
		label.setLocation(x + 5,y + 5);
		contentPane.add(label);
	}
	
	private void initDisplayLabels() {
		numNodes = new JLabel("0"); 
		addLabel(numNodes, 1*LABEL_WIDTH , 1 * LABEL_HEIGHT );
		
		rtShortestPath  = new JLabel("0");
		addLabel(rtShortestPath, 1*LABEL_WIDTH , 2 * LABEL_HEIGHT );
		
		rtDiameter = new JLabel("0");
		addLabel(rtDiameter, 1*LABEL_WIDTH , 3 * LABEL_HEIGHT );
		
		rtHops = new JLabel("0");
		addLabel(rtHops, 1*LABEL_WIDTH , 4 * LABEL_HEIGHT );
		
		
		strength = new JLabel("0");
		addLabel(strength, 1*LABEL_WIDTH , 5 * LABEL_HEIGHT );
		
		numNetworksLabel = new JLabel("0");
		addLabel(numNetworksLabel, 1*LABEL_WIDTH , 6 * LABEL_HEIGHT );
		
		totalNumberNodes = new JLabel("0");
		addLabel(totalNumberNodes, 1*LABEL_WIDTH , 7 * LABEL_HEIGHT );
	}

	public void setStatisticsRunner(StatisticsRunner stats) {
		this.stats = stats;
	}

	public int getNetworkNum() {
		return numNetworks;
	}

	public void setNetworkNum(int networkNum) {
		this.numNetworks = networkNum;
	}
	
	
}
