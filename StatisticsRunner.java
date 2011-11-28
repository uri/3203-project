/*************************************************************************************
 * ===================================================================================
 *
 * 
 * 
 * ===================================================================================
 *************************************************************************************/

import java.util.ArrayList;
import java.util.List;


public class StatisticsRunner {
	
	protected List<Network> listOfNetworks;
	protected List<DirectedNetwork> listOfDirectedNetworks;
	
	
	// Added by Uri -- may be temporary

	// Statistics
	protected int numNodes;
	
	// Run times
	protected float rtShortestPath;
	protected float rtDiameter;
	protected float rtHops;
	
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
		
		
	}
	
	
	/**
	 * 
	 */
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
	
	
	/**
	 * 
	 */
	public void lengthOfRoutes() {
		
	}
	
	
	/**
	 * 
	 */
	public void diameterOfNetwork() {
		
		if (listOfDirectedNetworks.size() < 1) return;
		
		System.out.println("Running diameterOfNetwork on " + listOfDirectedNetworks.size() + " directed networks...");
		
		
		int runningDistanceTotal = 0;
		for (DirectedNetwork net : listOfDirectedNetworks) {
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
			System.out.println("The biggest distance in this network is " + biggest.getDistance());
			
		}
		double average = (double)runningDistanceTotal/(double)listOfDirectedNetworks.size();
		System.out.println("Done running diameters. Average greatest distance is: " + average);
		
		
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
	public void setListOfNetworks(List<Network> listOfNetworks) {
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
	public void setListOfDirectedNetworks(List<DirectedNetwork> listOfDirectedNetworks) {
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


	/**
	 * @return
	 */
	public float getRtShortestPath() {
		return rtShortestPath;
	}


	/**
	 * @param rtShortestPath
	 */
	public void setRtShortestPath(float rtShortestPath) {
		this.rtShortestPath = rtShortestPath;
	}


	/**
	 * @return
	 */
	public float getRtDiameter() {
		return rtDiameter;
	}


	/**
	 * @param rtDiameter
	 */
	public void setRtDiameter(float rtDiameter) {
		this.rtDiameter = rtDiameter;
	}


	/**
	 * @return
	 */
	public float getRtHops() {
		return rtHops;
	}


	/**
	 * @param rtHops
	 */
	public void setRtHops(float rtHops) {
		this.rtHops = rtHops;
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
