import java.awt.Point;
import java.util.ArrayList;

public class Node {
	private Point loc;
	private double direction, angle;
	private ArrayList<Node> allEdges;
	private ArrayList<Integer> weights;
	private ArrayList<Node> mstEdges;

	public Node() {
		loc = new Point();
		allEdges = new ArrayList<Node>();
		weights = new ArrayList<Integer>();
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

	public void setAngle(double i) {
		angle = i;
	}
	
	public void setDirection(double i){
		direction = i;
	}
	
	public ArrayList<Integer> getWeights() {
		return weights;
	}
	
	
	/**
	 */
	public int getWeight(Node n) {
		
		// Weight will just be the hypotenous
		int x = this.loc.x - n.getX();
		int y = this.loc.y - n.getY();
		
		int hyp = (int)(Math.pow(x, 2) + Math.pow(y, 2));
		hyp = (int)(Math.pow(hyp, 0.5f));
		
		
		return hyp;
	}
	
	public int getWeight(Node n, int index) {
		int hyp = getWeight(n);
		
		// TODO: This may be complete wrong and cause null pointer.
		weights.add(index, hyp);
		
		return hyp;
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







