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
		
		MinimalSpanning();
		MaximalMatching();
		AntennaOrientation();
		
	}
	
	//Uri's Algorithm
	public void MinimalSpanning(){
		//this is a stub! Feel free to rename it and stuff
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
