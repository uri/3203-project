import java.util.ArrayList;


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
		//sensorlist = n.getSensorList();
	/*	temp = new Node(DEFAULTX,DEFAULTY);
		sensorlist.add(temp);
		temp = new Node(DEFAULTX-20,DEFAULTY-10);
		sensorlist.add(temp);*/
		strength = n.strength;
		
		attachNeighbours();
		
		MinimalSpanning();
		AntennaOrientation();
		
	}
	
	//Uri's Algorithm
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
				//System.out.println("A node in the neighbourlist");
				currentAngle = 0;
				double deltaX = (s.getX() - n.getX());
				double deltaY = (s.getY() - n.getY());
				//System.out.println("Delta X: " + deltaX + ". DeltaY: " + deltaY);
				if (deltaX == 0 && deltaY>0){
					currentAngle = 270;
				}
				else if (deltaX == 0 && deltaY<0){
					currentAngle = 90;
				}
				else if (deltaX>0 && deltaY <= 0){//+ -
					deltaY = -deltaY;
					x = deltaY/deltaX;
					currentAngle = Math.toDegrees(Math.atan(x));
				}
				else if (deltaX<0 && deltaY >= 0){//-+
					deltaX = -deltaX;
					x = deltaY/deltaX;
					currentAngle = 180 + Math.toDegrees(Math.atan(x));
				}
				else if (deltaX<0 && deltaY<=0){//- -
					x = deltaY/deltaX;
					currentAngle = 180 - Math.toDegrees(Math.atan(x));
				}
				else if (deltaX>0 && deltaY >= 0){//+ +
					x = deltaY/deltaX;
					currentAngle = 360 - Math.toDegrees(Math.atan(x));
				}
				//System.out.println("current angle is " + currentAngle);
				
				if (currentAngle < smallestAngle)
					smallestAngle =currentAngle;
				if (currentAngle > largestAngle)
					largestAngle = currentAngle;
				if(currentAngle < secondSmallestAngle && currentAngle > smallestAngle)
					secondSmallestAngle = currentAngle;
			}
			System.out.println("Second Smallest Angle: " + secondSmallestAngle);
			//if statement ensures the smallest possible angle is drawn
			if (secondSmallestAngle == 361)
				secondSmallestAngle = largestAngle;
			if (secondSmallestAngle-smallestAngle > 180){
				n.setDirection(secondSmallestAngle);
				n.setAngle(360+smallestAngle-secondSmallestAngle);
				System.out.println("Doing This!");
			}
			else{
				n.setDirection(smallestAngle);
				n.setAngle(largestAngle-smallestAngle);
			}
		}
	}

}
