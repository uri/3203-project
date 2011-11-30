/*************************************************************************************
 * ===================================================================================
 *
 * A DirectedNetwork extends a Network. It is used to different from the standard
 * network.
 * 
 * ===================================================================================
 *************************************************************************************/

import java.util.ArrayList;


public class DirectedNetwork extends Network{
	
	
	/**
	 * Constructor for the Directed Network
	 * @param n
	 */
	public DirectedNetwork(Network n){
		sensorlist = new ArrayList<Node>();
		//copy relevant data from the base network
		Node temp;
		//System.out.println("\nNew Directed Network Created");
		for (Node s: n.getSensorList()){
			temp = new Node(s.getX(),s.getY());
			sensorlist.add(temp);
		}
		strength = n.strength;
		
		attachNeighbours();
		
		minimalSpanning();
		maximalMatching();
		antennaOrientation();
		
		updateAllEdges();
	}
	
	
	
	/**
	 * Goes through each node of the sensor list - then goes through every edge (non-mst)
	 * and checks if they are still supposed to be neighbouring. 
	 */
	public void updateAllEdges(){
		for (Node n: sensorlist){
			for (int i = 0; i < n.getAllEdges().size();i++){
				Node s = n.getAllEdges().get(i);
				if (n.getRelativeAngle(s) < n.getDirection() || n.getRelativeAngle(s) > (n.getAngle()+n.getDirection()) ){
						n.removeAllEdge(s);
						i--;
				}
			}
		}
	}
	
	/**
	 * Prim's algorithm
	 */
	public void minimalSpanning(){
		int smallestDistance;
		Node origin = new Node();
		Node destination = new Node();
		ArrayList<Node> temp = new ArrayList<Node>();
		temp.add(sensorlist.get(0));
		
		for (int i = 0; i < sensorlist.size(); i++){
			smallestDistance = 1000;
			for(Node n : temp){
				for(Node s: n.getAllEdges()){
					if( (n.getWeight(s) < smallestDistance) && !temp.contains(s) ){
						smallestDistance = n.getWeight(s);
						origin = n;
						destination = s;
					}
				}
			}
			temp.add(destination);
			origin.addMSTEdge(destination);
			destination.addMSTEdge(origin);
		}
	}
	
	/**
	 * Orients the antenas
	 */
	public void antennaOrientation(){
		for (Node n: sensorlist){
			//System.out.println("SENSOR LIST. This node has " + n.getMSTEdges().size() + " edges and " + n.getAllEdges().size() + " neighbours" );
			double smallestAngle = 1000;
			double largestAngle = 0;
			double currentAngle = 0;
			double secondSmallestAngle = 361;
			double x;
			for (Node s: n.getMSTEdges()){

				currentAngle = n.getRelativeAngle(s);
				
				if (currentAngle < smallestAngle)
					smallestAngle =currentAngle;
				if (currentAngle > largestAngle)
					largestAngle = currentAngle;
				if(currentAngle < secondSmallestAngle && currentAngle > smallestAngle)
					secondSmallestAngle = currentAngle;
			}
			//if statement ensures the smallest possible angle is drawn
			if (secondSmallestAngle == 361)
				secondSmallestAngle = largestAngle;
			if (secondSmallestAngle-smallestAngle > 180){
				n.setDirection(secondSmallestAngle);
				n.setAngle(360+smallestAngle-secondSmallestAngle);
			}
			else{
				n.setDirection(smallestAngle);
				n.setAngle(largestAngle-smallestAngle);
			}
		}
	}
}











