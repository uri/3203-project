import java.util.ArrayList;


public class DirectedNetwork extends Network{
	
	public DirectedNetwork(Network n){
		//copy relevant data from the base network
		Node temp;
		for (Node s: n.getSensorList()){
			temp = new Node(s.getX(),s.getY());
			sensorlist.add(temp);
		}
		//sensorlist = n.getSensorList();
		strength = n.strength;
		
		attachNeighbours();
		
		MinimalSpanning();
		MaximalMatching();
		AntennaOrientation();
		
	}
	
	//Uri's Algorithm
	public void MinimalSpanning(){
		//this is a stub! Feel free to rename it and stuff
		//Algorithms.primMST(this);
		int smallestDistance;
		Node origin = new Node();
		Node destination = new Node();
		ArrayList<Node> temp = new ArrayList<Node>();
		ArrayList<Node> destinationList = new ArrayList<Node>();
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
	
	//Jason's Algorithm
	public void MaximalMatching(){
		//this is a stub! Feel free to rename it and stuff
	}
	
	//Eric's Algorithm
	public void AntennaOrientation(){
		//this is a stub! Feel free to rename it and stuff
		
		//this is just to demonstrate that the directed network is different from the parent one
		for(Node s: sensorlist){
			s.setAngle(90);
		}
	}

}
