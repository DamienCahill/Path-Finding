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
	private Node[][] squares;
	private int startx=0;
	private int starty=0;
	private int finishx=49;
	private int finishy=49;
	private int CurrentlyDrawing = 0;
	
	public int getCurrentlyDrawing() {
		return CurrentlyDrawing;
	}
	public void setCurrentlyDrawing(int currentlyDrawing) {
		CurrentlyDrawing = currentlyDrawing;
	}
	//Constructor	
	public Grid(int nbrOfSquares, int gridSize) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setNumberOfSquaresInGrid(nbrOfSquares);
		setGridSize(gridSize);
		setGridSquareSize(gridSize/SquaresInGridLength);
		setSquares(new Node[SquaresInGridLength][SquaresInGridLength]);
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
	public Node[][] getSquares() {
		return squares;
	}
	public void setSquares(Node[][] squares) {
		this.squares = squares;
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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int x = 0; x < SquaresInGridLength; x++) {	
			for(int y = 0; y < SquaresInGridLength; y++) {
				// 0 = start, 1 = finish, 2 = wall, 3 = empty, 4 = checked, 5 = backtrack/visualise
				switch(getSquares()[x][y].getType()) {
				case 0:
					if (x==startx && y ==starty) {
						g.setColor(Color.GREEN);
					} else {
						g.setColor(Color.WHITE);
						getSquares()[x][y].setType(3);
					}
					break;
				case 1:
					if (x==finishx && y ==finishy) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.WHITE);
						getSquares()[x][y].setType(3);
					}
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
		//getSquares()[finishx][finishy].setType(3); 
		Node n = new Node(CurrentlyDrawing,e.getX()/gridSquareSize, e.getY()/gridSquareSize);
		squares[e.getX()/gridSquareSize][e.getY()/gridSquareSize]= n;
		if (CurrentlyDrawing == 1) {
			finishx = n.getX();
			finishy = n.getY();
		}
		if (CurrentlyDrawing == 0) {
			startx = n.getX();
			starty = n.getY();
		}
		
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	//Mouse Listeners
	@Override
	public void mousePressed(MouseEvent e) {
		// create a new square object of selected type 
		// update the square in the squares list
		// repaint the grid with the new list of squares
		//Square s = new Square(1,e.getX()/gridSquareSize, e.getY()/gridSquareSize);
			///getSquares()[finishx][finishy].setType(3);
			//getSquares()[startx][starty].setType(3);
			Node n = new Node(CurrentlyDrawing,e.getX()/gridSquareSize, e.getY()/gridSquareSize);
			squares[e.getX()/gridSquareSize][e.getY()/gridSquareSize]= n;
			if (CurrentlyDrawing == 1) {
				finishx = n.getX();
				finishy = n.getY();
			}
			if (CurrentlyDrawing == 0) {
				startx = n.getX();
				starty = n.getY();
			}
			repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	public void populateSquares() {
		//Populate the square array with squares of type empty
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
				getSquares()[x][y]=s;
			}
		}
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