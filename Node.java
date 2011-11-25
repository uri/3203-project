import java.awt.Point;
import java.util.ArrayList;

public class Node {
	private Point loc;
	private double direction, angle;
	private ArrayList<Node> allEdges;
	private ArrayList<Node> mstEdges;

	public Node() {
		loc = new Point();
		allEdges = new ArrayList<Node>();
		mstEdges = new ArrayList<Node>();
	}
	
	public Node(int x, int y) {
		this();
		loc = new Point(x,y);
		direction = 0;
		angle = 360;
	}
	
	boolean equals (Node n) {
		return (this.loc.x == n.loc.x && this.loc.y == n.loc.y);
	}
	
	//returns x coordinate
	public int getX(){
		return loc.x;
	}
	//returns y coordinate
	public int getY(){
		return loc.y;
	}
	
	public double getDirection(){
		return direction;
	}
	
	public double getAngle(){
		return angle;
	}
	
	public ArrayList<Node> getAllEdges() {
		return allEdges;
	}

	public void addAllEdge(Node n) {
		allEdges.add(n);
	}
	//this removes an edge from the AllEdge list. It's rather confusingly named
	public void removeAllEdge(Node n){
		allEdges.remove(n);
		
	}

	public void setAngle(double i) {
		angle = i;
	}
	
	public void setDirection(double i){
		direction = i;
	}
	

	public int getWeight(Node n) {
		// Weight will just be the hypotenous
		int x = this.loc.x - n.getX();
		int y = this.loc.y - n.getY();
		
		int hyp = (int)(Math.pow(x, 2) + Math.pow(y, 2));
		hyp = (int)(Math.pow(hyp, 0.5f));
		
		return hyp;
	}
	
	public double getRelativeAngle(Node n){
		double currentAngle = 0;
		double x = 0;
		double deltaX = (n.getX() - getX());
		double deltaY = (n.getY() - getY());

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
		
		return currentAngle;
	}
	
	public ArrayList<Node> getMSTEdges() {
		return mstEdges;
	}

	public void setMSTEdge(ArrayList<Node> edges) {
		this.mstEdges = edges;
	}
	
	public void addMSTEdge(Node n){
		if (!mstEdges.contains(n))
			mstEdges.add(n);
		else
			return;
	}
}







