import javax.swing.JFrame;

public class PathFinding {
	JFrame frame;
	Grid grid;
	public static void main(String[] args) {
		
		new PathFinding();
	}
	public PathFinding() {
		// Call the method to create frame + draw grid
		drawGrid();
	}


	public void drawGrid() {
		// Create the JFrame and set its  attributes
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(540,560);
		frame.setTitle("Path Finding");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Create the grid, add it to the frame
		grid = new Grid(20, 500);
		grid.setBounds(10, 10,grid.getGridSize()+1, grid.getGridSize()+1);
		frame.getContentPane().add(grid);
	}
}