/************************************************************************************* 
 * ===================================================================================
 *
 * 
 * 
 * ===================================================================================
 *************************************************************************************/

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StatisticsRunner {
	
	protected ArrayList<Network> listOfNetworks;
	protected ArrayList<DirectedNetwork> listOfDirectedNetworks;
	
	
	// Added by Uri -- may be temporary

	// Statistics
	protected int numNodes;
	
	// Run times
	
	// TODO Set these in your methods
	protected double rtShortestPath;
	protected double rtDiameter;
	protected double rtHops;
	
	protected double omniRTShortestPath;
	protected double omniRTDiameter;
	protected double omniRTHops;
	
	
	protected double averageShortest;
	protected double averageDiameter;
	protected double averageLength;
	
	protected double omniAverageShortest;
	protected double omniAverageDiameter;
	protected double omniAverageLength;
	
	// Static stuff.
	protected float angle;
	protected float strength;
	
	
	
	
	/**
	 * @param numberOfNetworks
	 * @param numberOfNodesPerNetwork
	 * @param signalStrength
	 */
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
		
		this.setNumNodes(numberOfNodesPerNetwork);
		
		// compute the stats for the directed network
		long startTime = System.currentTimeMillis();
		this.setAverageDiameter(averageDiameterOfNetworks(listOfDirectedNetworks));
		long endTime = System.currentTimeMillis();
		this.setRtDiameter((double)(endTime - startTime));
		
		// omnidirectional network
		startTime = System.currentTimeMillis();
		this.setAverageDiameter(averageDiameterOfNetworks(listOfNetworks));
		endTime = System.currentTimeMillis();
		this.setOmniRTDiameter((double)(endTime - startTime));

		
		startTime = System.currentTimeMillis();
		this.setAverageShortest(averageOfShortestPathsForNetwork(listOfDirectedNetworks));
		endTime = System.currentTimeMillis();
		this.setRtShortestPath((double)(endTime - startTime));
		
		
		startTime = System.currentTimeMillis();
		this.setAverageLength(averageLengthOfNetworkList(listOfDirectedNetworks));
		endTime = System.currentTimeMillis();
		this.setRtHops((double)(endTime - startTime));
		
	}
	
	
	/**
	 * 
	 */
	public void shortestPaths() {
		if (listOfDirectedNetworks.size() < 1) return; // no funny business..
		
		// TODO: shortest paths doesn't work for omnidirectional networks!
		//System.out.println("Running shortest paths on " + listOfNetworks.size() + "  networks...");
		//System.out.println("Done running shortest paths. Average nodes in shortest-path: " + averageOfShortestPathsForNetwork(listOfNetworks));
		
		System.out.println("Running shortest paths on " + listOfDirectedNetworks.size() + " directed networks...");
		System.out.println("Done running shortest paths. Average nodes in shortest-path: " + averageOfShortestPathsForNetwork(listOfDirectedNetworks));
	}
	
	
	private double averageOfShortestPathsForNetwork(ArrayList<? extends Network> networks) {
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
		//System.out.println("Done.. the average was: " + averageDiameterOfNetworks());
		
	}
	
	
	private double averageDiameterOfNetworks(ArrayList<? extends Network> networks) {
		// find the shortest paths between every pair of nodes in the network
		// then pick the longest one.
		// that's the diameter
		// c.f. http://stackoverflow.com/questions/3174569/what-is-meant-by-diameter-of-a-network
		int runningTotalOfNetworkDiameters = 0;
		for (Network net : networks) {
			
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
			
			
			runningTotalOfNetworkDiameters += currentLongestPath;
		}
		
		return (double)runningTotalOfNetworkDiameters/(double)networks.size();
	}
	
	public void lengthOfRoutes() {
		
		if (listOfDirectedNetworks.size() < 1) return;
		
		// Doesn't work for omnidirectional because of shortestPaths
		//System.out.println("Running lengthOfNetwork on " + listOfDirectedNetworks.size() + " omnidirectional networks...");
		//System.out.println("Done running diameters. Average greatest distance is: " + averageLengthOfNetworkList(listOfNetworks));
		
		
		System.out.println("Running lengthOfNetwork on " + listOfDirectedNetworks.size() + " directed networks...");
		System.out.println("Done running diameters. Average greatest distance is: " + averageLengthOfNetworkList(listOfDirectedNetworks));
		
	}
	
	
	private double averageLengthOfNetworkList(ArrayList<? extends Network> networks) {
		
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

	
	
	/*******************************************************************
	 *		Getters and Setters
	 *******************************************************************/
	/**
	 * @return
	 */
	public List<Network> getListOfNetworks() {
		return listOfNetworks;
	}


	/**
	 * @param listOfNetworks
	 */
	public void setListOfNetworks(ArrayList<Network> listOfNetworks) {
		this.listOfNetworks = listOfNetworks;
	}


	/**
	 * @return
	 */
	public List<DirectedNetwork> getListOfDirectedNetworks() {
		return listOfDirectedNetworks;
	}


	/**
	 * @param listOfDirectedNetworks
	 */
	public void setListOfDirectedNetworks(ArrayList<DirectedNetwork> listOfDirectedNetworks) {
		this.listOfDirectedNetworks = listOfDirectedNetworks;
	}


	/**
	 * @return
	 */
	public int getNumNodes() {
		return numNodes;
	}


	/**
	 * @param numNodes
	 */
	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}
	
	
	public int getNumNetworks() {
		return listOfNetworks.size();
	}
	
	


	/**
	 * @return
	 */
	public double getRtShortestPath() {
		return rtShortestPath;
	}


	/**
	 * @param rtShortestPath
	 */
	public void setRtShortestPath(double rtShortestPath) {
		this.rtShortestPath = rtShortestPath;
	}


	/**
	 * @return
	 */
	public double getRtDiameter() {
		return rtDiameter;
	}


	/**
	 * @param rtDiameter
	 */
	public void setRtDiameter(double rtDiameter) {
		this.rtDiameter = rtDiameter;
	}


	/**
	 * @return
	 */
	public double getRtHops() {
		return rtHops;
	}


	/**
	 * @param rtHops
	 */
	public void setRtHops(double rtHops) {
		this.rtHops = rtHops;
	}


	/**
	 * @return the averageShortest
	 */
	public double getAverageShortest() {
		return averageShortest;
	}


	/**
	 * @param averageShortest the averageShortest to set
	 */
	public void setAverageShortest(double averageShortest) {
		this.averageShortest = averageShortest;
	}


	/**
	 * @return the averageDiameter
	 */
	public double getAverageDiameter() {
		return averageDiameter;
	}


	/**
	 * @param averageDiameter the averageDiameter to set
	 */
	public void setAverageDiameter(double averageDiameter) {
		this.averageDiameter = averageDiameter;
	}


	/**
	 * @return the averageLength
	 */
	public double getAverageLength() {
		return averageLength;
	}


	/**
	 * @param averageLength the averageLength to set
	 */
	public void setAverageLength(double averageLength) {
		this.averageLength = averageLength;
	}


	/**
	 * @return the omniRTShortestPath
	 */
	public double getOmniRTShortestPath() {
		return omniRTShortestPath;
	}


	/**
	 * @param omniRTShortestPath the omniRTShortestPath to set
	 */
	public void setOmniRTShortestPath(double omniRTShortestPath) {
		this.omniRTShortestPath = omniRTShortestPath;
	}


	/**
	 * @return the omniRTDiameter
	 */
	public double getOmniRTDiameter() {
		return omniRTDiameter;
	}


	/**
	 * @param omniRTDiameter the omniRTDiameter to set
	 */
	public void setOmniRTDiameter(double omniRTDiameter) {
		this.omniRTDiameter = omniRTDiameter;
	}


	/**
	 * @return the omniRTHops
	 */
	public double getOmniRTHops() {
		return omniRTHops;
	}


	/**
	 * @param omniRTHops the omniRTHops to set
	 */
	public void setOmniRTHops(double omniRTHops) {
		this.omniRTHops = omniRTHops;
	}


	/**
	 * @return the omniAverageShortest
	 */
	public double getOmniAverageShortest() {
		return omniAverageShortest;
	}


	/**
	 * @param omniAverageShortest the omniAverageShortest to set
	 */
	public void setOmniAverageShortest(double omniAverageShortest) {
		this.omniAverageShortest = omniAverageShortest;
	}


	/**
	 * @return the omniAverageDiameter
	 */
	public double getOmniAverageDiameter() {
		return omniAverageDiameter;
	}


	/**
	 * @param omniAverageDiameter the omniAverageDiameter to set
	 */
	public void setOmniAverageDiameter(double omniAverageDiameter) {
		this.omniAverageDiameter = omniAverageDiameter;
	}


	/**
	 * @return the omniAverageLength
	 */
	public double getOmniAverageLength() {
		return omniAverageLength;
	}


	/**
	 * @param omniAverageLength the omniAverageLength to set
	 */
	public void setOmniAverageLength(double omniAverageLength) {
		this.omniAverageLength = omniAverageLength;
	}


	/**
	 * @return
	 */
	public float getAngle() {
		return angle;
	}


	/**
	 * @param angle
	 */
	public void setAngle(float angle) {
		this.angle = angle;
	}


	/**
	 * @return
	 */
	public float getStrength() {
		return strength;
	}


	/**
	 * @param strength
	 */
	public void setStrength(float strength) {
		this.strength = strength;
	}
	
	
	

}