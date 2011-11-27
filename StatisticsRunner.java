import java.util.ArrayList;


public class StatisticsRunner {
	
	protected Network network;
	
	public StatisticsRunner(Network theNetwork) {
		super();
		
		network = theNetwork;
		
	}
	
	
	public void shortestPaths() {
		System.out.println("Running shortest paths...");
	}
	
	
	public void lengthOfRoutes() {
		
	}
	
	
	public void diameterOfNetwork() {
		ArrayList<Node> nodes = network.getSensorList();
		
		Node n1 = nodes.get(0);
		Node n2 = nodes.get(1);
		
		
	}
	
	
	

}
