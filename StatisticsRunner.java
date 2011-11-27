import java.util.ArrayList;
import java.util.List;


public class StatisticsRunner {
	
	protected List<Network> listOfNetworks;
	protected List<DirectedNetwork> listOfDirectedNetworks;
	
	public StatisticsRunner(int numberOfNetworks, int numberOfNodesPerNetwork, int signalStrength) {
		super();
		
		listOfNetworks = new ArrayList<Network>();
		listOfDirectedNetworks = new ArrayList<DirectedNetwork>();
		
		// generate some random networks
		for (int i = 0; i < numberOfNetworks; i++) {
			Network network = new Network(numberOfNodesPerNetwork, signalStrength);
			DirectedNetwork directedNet = new DirectedNetwork(network);
			
			listOfNetworks.add(network);
			listOfDirectedNetworks.add(directedNet);
		}
		
		
	}
	
	
	public void shortestPaths() {
		if (listOfDirectedNetworks.size() < 1) return; // no funny business..
		System.out.println("Running shortest paths on " + listOfDirectedNetworks.size() + " directed networks...");
		
		int runningTotal = 0;
		
		for (DirectedNetwork net : listOfDirectedNetworks) {
			Node start = net.getSensorList().get(0);
			Node end = net.getSensorList().get(net.getSensorList().size() - 1);
			net.shortestPath(start, end);
			
			List<Node> shortestPath = net.getShortestPathList();
			System.out.println("Nodes in the shortest path...printing " + shortestPath.size() + " nodes");
			for (Node node : shortestPath) {
				System.out.println(node.toString());
			}
			runningTotal += shortestPath.size();
		}
		double average = (double)runningTotal/(double)listOfDirectedNetworks.size();
		System.out.println("Done running shortest paths. Average nodes in shortest-path: " + average);
	}
	
	
	public void lengthOfRoutes() {
		
	}
	
	
	public void diameterOfNetwork() {
//		ArrayList<Node> nodes = network.getSensorList();
//		
//		Node n1 = nodes.get(0);
//		Node n2 = nodes.get(1);
		
		
	}
	
	
	

}
