import javax.swing.JFrame;

public class Frame extends JFrame{

	public static final String FRAME_NAME = "3203 - Networking";
	
	
	public Frame(String name) {
		super(name);

		// Make sure it exists properly
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the JPanel
		getContentPane().add(new Panel());

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