/*************************************************************************************
 * ===================================================================================
 *
 * This is the back end. This class stores lists and contains algorithms for computing
 * Shortest paths, diameter etc.
 * 
 * ===================================================================================
 *************************************************************************************/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;



public class Network {
	ArrayList<Node> sensorlist;

	List<Node> shortestPathList;
	List<Node> diameterPathList;
	int strength;

	protected static int DEFAULTX = 100;
	protected static int DEFAULTY = 100;
	
	protected StatisticsRunner stats;
	
	
	/**
	 * CONSTRUCTOR
	 * @author ugorelik
	 *
	 */
	public Network() {
		shortestPathList = null;
		diameterPathList = null;
		strength = 50;
		sensorlist = new ArrayList<Node>();
		sensorlist.add(new Node(DEFAULTX, DEFAULTY));
	}

	
	/**
	 * CONSTRUCTOR
	 * Initializes the strength and the number of nodes
	 * @author ugorelik
	 *
	 */
	public Network(int n, int s) {
		int x = 0;
		strength = s;
		Node temp = new Node();
		Random rand = new Random();
		sensorlist = new ArrayList<Node>();
		// starting node
		sensorlist.add(new Node(DEFAULTX, DEFAULTY));
		for (int i = 1; i < n; i++) {
			x = rand.nextInt(i);// selects a random node to place the new node
								// near
			temp = sensorlist.get(x);

			// the maximum distance in either direction from any other node
			int max = (int) Math.sqrt(strength * strength / 2);

			Node newNode = null;
			// Make sure we don't add it twice
			while (sensorlist.contains(newNode) || newNode == null) {

				newNode = new Node(temp.getX() + rand.nextInt(max), temp.getY()
						+ rand.nextInt(max));
			}
			sensorlist.add(newNode);
		}
		
		attachNeighbours();
	}

	
	/**
	 * 
	 */
	public void maximalMatching() {
		Set<Node> matchedSet = new HashSet<Node>();
		
		for (Node n : sensorlist) {
			
			if (n.getMatch() != null) continue;
			
			int shortestEdgeWeight = Integer.MAX_VALUE;
			Node closestNeighbour = new Node();
			
			for (Node neighbour : n.getMSTEdges()) {
				
				if (neighbour.getMatch() != null) continue; // Saftey may not be needed	
				
				// Find a neighbour with no neighbours
				if (neighbour.getMSTEdges().size() == 1) {
					n.setMatch(neighbour);
					neighbour.setMatch(n);
					
					matchedSet.add(n);
					matchedSet.add(neighbour);
					break;
				}

				// Pick the one with the shortest distance
				if (n.getWeight(neighbour) < shortestEdgeWeight) {
					closestNeighbour = neighbour;
					shortestEdgeWeight = n.getWeight(neighbour);
				}
				
			}
			
			// At this point we have the shortest distance, or a lone neighbour
			if (n.getMatch() == null) {
				n.setMatch(closestNeighbour);
				closestNeighbour.setMatch(n);
			}
		}
	}
	
	/**
	 * Finds the shortest path between two nodes. Sets a networks 
	 * shortestPathList variable.
	 * 
	 * @param startNode
	 * @param endNode
	 */
	public void shortestPath(Node startNode, Node endNode) {
		
		// TEMP FIX
		startNode.setPredecessor(null);
		
		shortestPathList = new ArrayList<Node>();
		
		// Check if we're checking the same node
		if(startNode.equals(endNode)) {
			shortestPathList.add(startNode);
			return;
		}
		
		List<Node> pathedList = dijkstra(sensorlist, startNode);

		Node end = null;

		// Find the node
		for (Node n : pathedList) {
			if (endNode.equals(n))
				end = n;
		}

		
		//System.out.println("This is the weight to the last node: "
		//		+ pathedList.get(pathedList.size() - 1).getDistance());
		do {
			
			// TEMP FIX
			if (end == null) break;
			shortestPathList.add(end);
			end = end.getPredecessor();
		} while (end != null);

	}
	
	/**
	 *  Maybe this should return instead of setting?
	 */
	public void diameterPathList() {
		Node startNode = this.sensorlist.get(0);
		List<Node> pathedList = dijkstra(sensorlist, startNode);
		
		Node endNode = new Node();
		endNode.setDistance(Integer.MIN_VALUE);
		
		for (Node n : sensorlist) {
			if (n.getDistance() > endNode.getDistance()) {
				endNode = n;
			}
		}
		
		diameterPathList = new ArrayList<Node>();
		
		do {
			diameterPathList.add(endNode);
			endNode = endNode.getPredecessor();
		} while (endNode != null);
		
	}

	
	
	/**
	 * Runs dijkstra's algorithm based on a graph and a startingNode
	 */
	public List<Node> dijkstra(ArrayList<Node> graph, Node sNode) {

		// Set all the nodes to infinity
		for (Node n : graph) {
			n.setDistance(Integer.MAX_VALUE);
		}

		// Make a list of visited nodes
		Set<Node> visited = new HashSet<Node>();
		List<Node> unvisited = new ArrayList<Node>();

		Node startNode = null;
		
		if (sNode == null) {
			graph.get(0);	
		} else {
			startNode = sNode;
		}
		
		
		startNode.setDistance(0);
		visited.add(startNode);
		
		for (Node edge : startNode.getMSTEdges()) {
			int distanceTo = startNode.getWeight(edge);
			edge.setDistance(distanceTo);
			edge.setPredecessor(startNode);
			unvisited.add(edge);
		}
		
		while (!unvisited.isEmpty()) {
			Node cur = unvisited.get(unvisited.size() - 1);
			for (Node edge : cur.getMSTEdges()) {

				if (!visited.contains(edge)) {
					unvisited.add(edge);

					int distanceTo = cur.getWeight(edge) + cur.getDistance();

					// If the distance is smaller than it's current distance we
					// update it.
					if (distanceTo < edge.getDistance()) {
						edge.setDistance(distanceTo);
						
						// Set it to the node we went through
						edge.setPredecessor(cur);
					}

				}

			}
			visited.add(cur);
			unvisited.remove(cur);
		}
		return new ArrayList<Node>(visited);
	}

	/**
	 * Populates the allEdges list. Run after a network is usually created
	 */
	protected void attachNeighbours() {

		// Double loop all the way accross the sky

		for (Node outer : sensorlist) {
			for (Node inner : sensorlist) {
				int currentDistance = outer.getWeight(inner);
				// Add a neighbour
				if (currentDistance <= strength && currentDistance > 0) {
					outer.addAllEdge(inner);
				}
			}
		}
	}

	
	
	/*******************************************************************
	 *		Getters and Setters
	 *******************************************************************/
	
	public ArrayList<Node> getSensorList() {
		return sensorlist;
	}
	
	
	public StatisticsRunner getStatisticsRunner() {
		return stats;
	}
	

	public List<Node> getShortestPathList() {
		return shortestPathList;
	}

	public void setShortestPathList(List<Node> shortestPathList) {
		this.shortestPathList = shortestPathList;
	}


}