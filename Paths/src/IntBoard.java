import java.util.*;

public class IntBoard {

	private int numRows, numColumns;
	private Map<Integer, ArrayList<BoardCell>> board = new HashMap<Integer, ArrayList<BoardCell>>();
	private Map<BoardCell, LinkedList<BoardCell>> adjacents;
	
	public IntBoard(int row, int column) {
		super();
		this.numRows = row;
		this.numColumns = column;
		// TODO Auto-generated constructor stub
	}
	public BoardCell getCell(int row, int col){
		return new BoardCell();
	}
	public void calcAdjacencies(){
		
	}
	public void calcTargets(BoardCell cell, int diceRoll){
		
	}
	public Set<BoardCell> getTargets(){
		Set<BoardCell> board = new HashSet<BoardCell>();
		return board;
	}
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		
		return new LinkedList<BoardCell>();
	}
	

	
}
