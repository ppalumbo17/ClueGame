import java.util.*;

public class IntBoard {

	private int numRows, numColumns;
	private BoardCell[][] boardMap;
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	
	public IntBoard(int row, int column) {
		super();
		this.numRows = row;
		this.numColumns = column;
		adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		boardMap = new BoardCell[numRows][numColumns];
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		initializeBoard();
	}
	
	public void initializeBoard(){
		//
		// PLACEHOLDER FOR TESTS -- NOT FINAL
		//
		for(int i = 0;i < numRows; i++){
			for(int j = 0; j< numColumns; j++){
				boardMap[i][j] = new BoardCell(i,j);
			}
		}
		
		for(int i = 0;i < numRows; i++){
			for(int j = 0; j< numColumns; j++){
				calcAdjacencies(boardMap[i][j]);
			}
		}
		
	}
	
	public BoardCell getCell(int row, int col){
		return boardMap[row][col];
	}
	
	public void calcAdjacencies(BoardCell currentCell){
		adjMtx.put(currentCell, new LinkedList<BoardCell>());
		//checks the cell above currentCell
		if(currentCell.getRow() > 0){
			adjMtx.get(currentCell).add(boardMap[currentCell.getRow() -1][currentCell.getColumn()]);
		}
		//checks below currentCell
		if((currentCell.getRow()+1) <= (numRows - 1)){
			adjMtx.get(currentCell).add(boardMap[currentCell.getRow() +1][currentCell.getColumn()]);
		}
		//checks to the right
		if((currentCell.getColumn()+1) <= (numColumns -1)){
			adjMtx.get(currentCell).add(boardMap[currentCell.getRow()][currentCell.getColumn() + 1]);
		}
		//checks to the left
		if(currentCell.getColumn() > 0){
			adjMtx.get(currentCell).add(boardMap[currentCell.getRow()][currentCell.getColumn() - 1]);
		}
	}
	
	public void calcTargets(BoardCell cell, int diceRoll){
		visited.add(cell);
		findAllTargets(cell, diceRoll);
	}
	
	public void findAllTargets(BoardCell cell, int numSteps){
		LinkedList<BoardCell> adjacentCells = new LinkedList<BoardCell>(adjMtx.get(cell));
		for(BoardCell x:adjacentCells){
			if(!visited.contains(x)){
				
				visited.add(x);
				if(numSteps == 1){
					targets.add(x);
				}else{
					findAllTargets(x, numSteps -1);
				}
				visited.remove(x);
			}
			
		}
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		return new LinkedList<BoardCell>(adjMtx.get(cell));
	}
	

	
}
