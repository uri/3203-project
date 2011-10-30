import java.util.ArrayList;

public class Terminal {
	
	
	Controller control;
	public Network network; // Public for testing
	
	
	
	Terminal(String [] args) {
		
		network = new Network();
		control = new Controller(args, network);
	}
	
	
	
	
	
	public static void main (String [] args) {
		Terminal test = new Terminal(args);
		
		for (Node n : test.network.getNodeList()) {
			System.out.println(n.strength);
		}
	}
}