import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JPanel;



public class Grid extends JPanel implements MouseListener, MouseMotionListener {
	//Variables
	private int SquaresInGridLength; // 20 => 20x20 grid
	private int gridSize;// size of grid component  
	private int gridSquareSize; // gridSize/numberOfSquaresInGrid
	private Node[][] nodes;
	private int startx=0;
	private int starty=0;
	private int finishx=49;
	private int finishy=49;
	private int currentlyDrawing = 2;
	
	//Constructor	
	public Grid(int nbrOfSquares, int gridSize) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setNumberOfSquaresInGrid(nbrOfSquares);
		setGridSize(gridSize);
		setGridSquareSize(gridSize/SquaresInGridLength);
		setNodes(new Node[SquaresInGridLength][SquaresInGridLength]);
		populateSquares();
	}
	//Getters&Setters
	public int getNumberOfSquaresInGrid() {
		return SquaresInGridLength;
	}
	public void setNumberOfSquaresInGrid(int numberOfSquaresInGrid) {
		this.SquaresInGridLength = numberOfSquaresInGrid;
	}
	public int getGridSize() {
		return gridSize;
	}
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	public int getGridSquareSize() {
		return gridSquareSize;
	}
	public void setGridSquareSize(int gridSquareSize) {
		this.gridSquareSize = gridSquareSize;
	}
	public int getFinishx() {
		return finishx;
	}
	public void setFinishx(int finishx) {
		this.finishx = finishx;
	}
	public int getFinishy() {
		return finishy;
	}
	public void setFinishy(int finishy) {
		this.finishy = finishy;
	}
	public Node[][] getNodes() {
		return nodes;
	}
	public void setNodes(Node[][] nodes) {
		this.nodes = nodes;
	}
	public int getStartx() {
		return startx;
	}
	public void setStartx(int startx) {
		this.startx = startx;
	}
	public int getStarty() {
		return starty;
	}
	public void setStarty(int starty) {
		this.starty = starty;
	}
	public int getCurrentlyDrawing() {
		return currentlyDrawing;
	}
	public void setCurrentlyDrawing(int currentlyDrawing) {
		this.currentlyDrawing = currentlyDrawing;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int x = 0; x < SquaresInGridLength; x++) {	
			for(int y = 0; y < SquaresInGridLength; y++) {
				// 0 = start, 1 = finish, 2 = wall, 3 = empty, 4 = checked, 5 = backtrack/path 
				switch(getNodes()[x][y].getType()) {
					case 0:
						g.setColor(Color.GREEN);
						break;
					case 1:
						g.setColor(Color.RED);
						break;
					case 2:
						g.setColor(Color.BLACK);
						break;
					case 3:
						g.setColor(Color.WHITE);
						break;
					case 4:
						g.setColor(Color.YELLOW);
						break;
					case 5:
						g.setColor(Color.BLUE);
						break;
				}
				g.fillRect(x*gridSquareSize,y*gridSquareSize,gridSquareSize,gridSquareSize);
				g.setColor(Color.BLACK);
				g.drawRect(x*gridSquareSize,y*gridSquareSize,gridSquareSize,gridSquareSize);
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			if (currentlyDrawing == 2 || currentlyDrawing == 3) {
				if (nodes[e.getX()/gridSquareSize][e.getY()/gridSquareSize].getType() > 1) {
					Node n = new Node(currentlyDrawing,e.getX()/gridSquareSize, e.getY()/gridSquareSize);
					nodes[e.getX()/gridSquareSize][e.getY()/gridSquareSize] = n;
					repaint();
				}
			}
		} catch (Exception ex) {
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		try {
			//Create a new node with relevant type
			Node newNode = new Node(currentlyDrawing,e.getX()/gridSquareSize, e.getY()/gridSquareSize);
			// add the node to the node list
			// if its a start of end node, replace the existing start or end node
			if (currentlyDrawing == 1) {
				nodes[finishx][finishy].setType(3);
				finishx = newNode.getX();
				finishy = newNode.getY();
			} else if (currentlyDrawing == 0) {
				nodes[startx][starty].setType(3);
				startx = newNode.getX();
				starty = newNode.getY();
			}
			nodes[e.getX()/gridSquareSize][e.getY()/gridSquareSize] = newNode;
			repaint();
		} catch (Exception ex) {
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	public void populateSquares() {
		//Populate the node array with nodes of type empty
		// the constructor accepts (int type, int x, int y) so we normally pass 3 as type since 3 = empty type 
		// if its start we set it to 0
		// if its end we set it to 1
		// 0 = start, 1 = finish, 2 = wall, 3 = empty, 4 = checked, 5 = finalpath
		for(int x = 0; x < SquaresInGridLength; x++) {	
			for(int y = 0; y < SquaresInGridLength; y++) {
				int type=3;
				if (x==startx && y==starty) {
					type=0;
				}
				if (x==finishx && y == finishy) {
					type=1;
				}
				Node s = new Node(type,x,y);
				getNodes()[x][y]=s;
			}
		}
		repaint();
		/* 
		 * debugging code
		for(int x = 0; x < numberOfSquaresInGrid; x++) {	
			for(int y = 0; y < numberOfSquaresInGrid; y++) {
				System.out.println(squares[x][y].toString());
			}
		}
		*/
	}
}