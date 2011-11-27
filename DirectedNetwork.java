import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DirectedNetwork extends Network{
	
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
		
		MinimalSpanning();
		AntennaOrientation();
		UpdateAllEdges();
	}
	
	public void UpdateAllEdges(){
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
	
	public void MinimalSpanning(){
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
	
	//Eric's Algorithm
	public void AntennaOrientation(){
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











