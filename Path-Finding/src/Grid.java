import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;



public class Grid extends JPanel implements MouseListener, MouseMotionListener {
	//Variables
	private int numberOfSquaresInGrid; // 20 => 20x20 grid
	private int gridSize;// size of grid component 
	private int gridSquareSize; // gridSize/numberOfSquaresInGrid
	
	//Constructor	
	public Grid(int nbrOfSquares, int gridSize) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setNumberOfSquaresInGrid(nbrOfSquares);
		setGridSize(gridSize);
		setGridSquareSize(gridSize/numberOfSquaresInGrid);
		
	}
	//Getters&Setters
	public int getNumberOfSquaresInGrid() {
		return numberOfSquaresInGrid;
	}

	public void setNumberOfSquaresInGrid(int numberOfSquaresInGrid) {
		this.numberOfSquaresInGrid = numberOfSquaresInGrid;
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

		
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		for(int x = 0; x < numberOfSquaresInGrid; x++) {	
			for(int y = 0; y < numberOfSquaresInGrid; y++) {
				g.setColor(Color.WHITE);
				g.fillRect(x*gridSquareSize,y*gridSquareSize,gridSquareSize,gridSquareSize);
				g.setColor(Color.BLACK);
				g.drawRect(x*gridSquareSize,y*gridSquareSize,gridSquareSize,gridSquareSize);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

		
}