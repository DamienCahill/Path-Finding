import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;


public class PathFinding {
	JFrame frame;
	Grid grid;
	boolean solving=false;
	int pathLength=0;
	int checks=0;
	JButton btnStart = new JButton("Start");
	public static void main(String[] args) {
		new PathFinding();
	}
	public PathFinding() {
		// Create the JFrame and set its  attributes
				frame = new JFrame();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setSize(535,635);
				frame.setTitle("Path Finding");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				
				// Create the grid, add it to the frame
				grid = new Grid(50, 500);
				grid.setBounds(10, 10,grid.getGridSize()+1, grid.getGridSize()+1);
				frame.getContentPane().add(grid);
		frame.getContentPane().add(btnStart);
		btnStart.setBounds(20,521,100,28);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(20, 561, 100, 28);
		frame.getContentPane().add(btnReset);
		
		JComboBox typeCBO = new JComboBox();
		frame.getContentPane().add(typeCBO);
		typeCBO.setBounds(264, 523, 106, 25);
		
		JLabel lblSelectType = new JLabel("Select Type to draw");
		frame.getContentPane().add(lblSelectType);
		lblSelectType.setBounds(139, 524, 120, 25);
		
		btnStart.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solving=true;
				AStar();
			}
		});
	}
	public void drawGrid() {
	}
	public void AStar() {
		ArrayList<Node> priority = new ArrayList<Node>();
		priority.add(grid.getSquares()[grid.getStartx()][grid.getStarty()]);
		while(solving) {
			if(priority.size() <= 0) {
				solving = false;
				break;
			}
			int hops = priority.get(0).getHops()+1;
			ArrayList<Node> explored = exploreNeighbors(priority.get(0),hops);
			if(explored.size() > 0) {
				priority.remove(0);
				priority.addAll(explored);
				grid.repaint();
			} else {
				priority.remove(0);
			}
			sortQue(priority);	
		}
	}
	
	
	public ArrayList<Node> sortQue(ArrayList<Node> sort) {	
		int positonBeingChecked = 0;
		while(positonBeingChecked < sort.size()) {
			int currentHighestPriority = positonBeingChecked;
			// loop through the list and if the next node being checked while looping has a lower distance to end than
			// currentHighestPrioty node, set that node to the currentHighestPriority
			for(int i = positonBeingChecked+1; i < sort.size(); i++) {
				int currentlyLowerInList = i;
				if(sort.get(currentlyLowerInList).getEuclidDist(grid.getFinishx(),grid.getFinishy())+sort.get(currentlyLowerInList).getHops() < sort.get(currentHighestPriority).getEuclidDist(grid.getFinishx(),grid.getFinishy())+sort.get(currentHighestPriority).getHops())
					// the current highest priority node has a distance to end larger than
					//a node lower in the list, so we keep track of the index of the node lower in the list
					currentHighestPriority = currentlyLowerInList;
			}
			
			// the currentHighestPriority has changed
			// so swap the currentHighest with the node we discovered to be the node with the lowest distance to end
			if(positonBeingChecked != currentHighestPriority) { 
				Node temp = sort.get(positonBeingChecked);
				sort.set(positonBeingChecked, sort.get(currentHighestPriority));
				sort.set(currentHighestPriority, temp);
			}	
			positonBeingChecked++;
		}
		return sort;
	}
	
	public ArrayList<Node> exploreNeighbors(Node current, int hops) {	
		ArrayList<Node> exploredNodes = new ArrayList<>();	
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int neighbourX = current.getX()+i;
				int neighbourY = current.getY()+j;
				// checks node is on grid (within bounds 0 and number of squares in grid length)
				if((neighbourX > -1 && neighbourX < grid.getNumberOfSquaresInGrid()) && (neighbourY > -1 && neighbourY < grid.getNumberOfSquaresInGrid())) {	
					Node neighbor = grid.getSquares()[neighbourX][neighbourY];
					// and checks node is not a wall and hasn't been checked
					if((neighbor.getHops()==-1 || neighbor.getHops() > hops) && neighbor.getType()!=2) {	
						explore(neighbor, current.getX(), current.getY(), hops);	
						exploredNodes.add(neighbor);	
					}
				}
			}
		}
		return exploredNodes;
	}
	
	public void explore(Node current, int lastx, int lasty, int hops) {	//EXPLORE A NODE
		//Explore the square if its not the start or finish square, set it to explored
		if(current.getType()!=0 && current.getType() != 1) 	
			current.setType(4);	
		// keep track of square it came from
		current.setLastSquare(lastx, lasty);	
		// set hops
		current.setHops(hops);	
		// if we find the finish node, backtrack
		if(current.getType() == 1) {	
			//System.out.println("found");
			backtrack(current.getLastx(), current.getLasty(), hops);
			solving = false;
		}
	}
	
	public void backtrack(int lx, int ly, int hops) {
		// backtrack to find path back to start from finish
		// sets the type of node to 5 (Explored type) so we can visualise the path
		while(hops > 1) {	
			Node current = grid.getSquares()[lx][ly];
			current.setType(5);
			lx = current.getLastx();
			ly = current.getLasty();
			hops--;
		}
	}
}