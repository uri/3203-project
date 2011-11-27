import java.util.ArrayList;
import java.util.List;


public class StatisticsRunner {
	
	protected ArrayList<Network> listOfNetworks;
	protected ArrayList<DirectedNetwork> listOfDirectedNetworks;
	
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
		
		// TODO: shortest paths doesn't work for omnidirectional networks!
		//System.out.println("Running shortest paths on " + listOfNetworks.size() + "  networks...");
		//System.out.println("Done running shortest paths. Average nodes in shortest-path: " + averageOfShortestPathsForNetwork(listOfNetworks));
		
		System.out.println("Running shortest paths on " + listOfDirectedNetworks.size() + " directed networks...");
		System.out.println("Done running shortest paths. Average nodes in shortest-path: " + averageOfShortestPathsForNetwork(listOfDirectedNetworks));
	}
	
	
	public double averageOfShortestPathsForNetwork(ArrayList<? extends Network> networks) {
		int runningTotal = 0;
		
		for (Network net : networks) {
			Node start = net.getSensorList().get(0);
			Node end = net.getSensorList().get(net.getSensorList().size() - 1);
			net.shortestPath(start, end);
			
			List<Node> shortestPath = net.getShortestPathList();
			runningTotal += shortestPath.size();
		}
		return (double)runningTotal/(double)networks.size();
	}
	
	
	public void diameterOfNetwork() {

		System.out.println("Going to get the average diameter of " + listOfDirectedNetworks.size() + " directed networks");
		System.out.println("Done.. the average was: " + averageDiameterOfDirectedNetworks());
		
	}
	
	
	public double averageDiameterOfDirectedNetworks() {
		// find the shortest paths between every pair of nodes in the network
		// then pick the longest one.
		// that's the diameter
		// c.f. http://stackoverflow.com/questions/3174569/what-is-meant-by-diameter-of-a-network
		int runningTotalOfNetworkDiameters = 0;
		for (Network net : listOfDirectedNetworks) {
			
			int currentLongestPath = 0;
			for (Node startNode : net.getSensorList()) {
				ArrayList<Node> allOtherNodes = new ArrayList<Node>(net.getSensorList());
				allOtherNodes.remove(startNode);
				
				// iterate over all the other nodes
				for (Node endNode : allOtherNodes) {
					// get the shortest path between them.
					
					net.shortestPath(startNode, endNode);
					List<Node> shortestPath = net.getShortestPathList();
					if (shortestPath.size() > currentLongestPath)
						currentLongestPath = shortestPath.size();
				}
			}
			
			System.out.println("The longest shortest path for this network is: " + currentLongestPath);
			runningTotalOfNetworkDiameters += currentLongestPath;
		}
		
		return (double)runningTotalOfNetworkDiameters/(double)listOfDirectedNetworks.size();
	}
	
	public void lengthOfRoutes() {
		
		if (listOfDirectedNetworks.size() < 1) return;
		
		// Doesn't work for omnidirectional because of shortestPaths
		//System.out.println("Running lengthOfNetwork on " + listOfDirectedNetworks.size() + " omnidirectional networks...");
		//System.out.println("Done running diameters. Average greatest distance is: " + averageLengthOfNetworkList(listOfNetworks));
		
		
		System.out.println("Running lengthOfNetwork on " + listOfDirectedNetworks.size() + " directed networks...");
		System.out.println("Done running diameters. Average greatest distance is: " + averageLengthOfNetworkList(listOfDirectedNetworks));
		
	}
	
	
	public double averageLengthOfNetworkList(ArrayList<? extends Network> networks) {
		
		int runningDistanceTotal = 0;
		for (Network net : networks) {
			Node start = net.getSensorList().get(0);
			Node end = net.getSensorList().get(net.getSensorList().size() - 1);
			net.shortestPath(start, end);
			
			List<Node> shortestPath = net.getShortestPathList();
			
			Node biggest = start;
			for (Node node : shortestPath) {
				if (node.getDistance() > biggest.getDistance())
					biggest = node;
			}
			
			runningDistanceTotal += biggest.getDistance();
			
		}
		return (double)runningDistanceTotal/(double)networks.size();
	}
	

}
