
public class Node {
	
	//Variables
	private int type;
	private int x;
	private int y;
	private double distanceToEnd;
	private int lastx;
	private int lasty;
	private int hops;
	
	//Constructor
	public Node(int type, int x, int y) {
		this.type = type;
		this.x=x;
		this.y=y;
		hops=-1;
		
		// if its the starting node then we made the hops 0
		if (type==0)
			hops=0;
		}
	
	//Getters & Setters
	public int getHops() {
		return hops;
	}

	public void setHops(int hops) {
		this.hops = hops;
	}

	public double getDistanceToEnd() {
		return distanceToEnd;
	}

	public void setDistanceToEnd(double distanceToEnd) {
		this.distanceToEnd = distanceToEnd;
	}

	public int getLastx() {
		return lastx;
	}

	public void setLastx(int lastx) {
		this.lastx = lastx;
	}

	public int getLasty() {
		return lasty;
	}

	public void setLasty(int lasty) {
		this.lasty = lasty;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	//combo of setX and setY
	public void setLastSquare(int x, int y) {
		setLastx(x);
		setLasty(y);
	}
	
	//toString (Used for debugging)
	@Override
	public String toString() {
		return "Node [type=" + type + ", x=" + x + ", y=" + y + ", distanceToEnd=" + distanceToEnd + ", lastx="
				+ lastx + ", lasty=" + lasty + ", hops=" + hops + "]";
	}

	// returns Euclidean distance to end node
	public double getEuclidDist(int finishx, int finishy) {		
		int xdif = Math.abs(x- finishx);
		int ydif = Math.abs(y-finishy);
		distanceToEnd = Math.sqrt((xdif*xdif)+(ydif*ydif));
		return distanceToEnd;
	}
	

}
