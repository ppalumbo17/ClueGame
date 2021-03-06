package clueGame;
import clueGame.BoardCell;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import clueGame.RoomCell.DoorDirection;

import java.util.LinkedList;


public class Board {
	private BoardCell[][] boardLayout;
	private Map<Character, String> rooms;
	private String config;
	private int numRows;
	private int numColumns;
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	
	public Board(){
		adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
	}
	
	
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException{
		FileReader reader = null;
		Scanner in = null;
		String[] temp;
		ArrayList<String[]> layout = new ArrayList<String[]>();
		try{
			reader = new FileReader(config);
			in = new Scanner(reader);
		}catch (FileNotFoundException e){
			throw new FileNotFoundException();
		}
		
		while(in.hasNextLine()){
			temp = in.nextLine().split(",");
			layout.add(temp);
			
		}
		in.close();
		
		numRows = layout.size();
		numColumns = layout.get(0).length;
		boardLayout = new BoardCell[numRows][numColumns];
		
		
		//Checking if missing any Columns in any row
		for(String[] x:layout){
			if(x.length != numColumns){
				throw new BadConfigFormatException("Error - Incorrect config format (rows not even!)");
			}
		}
		
		for(int i =0;i<numRows;i++){
			for(int j=0; j<numColumns;j++){
				
				//Error Checking each entry in config file
				if(layout.get(i)[j].length() > 2){
					throw new BadConfigFormatException("Error - Incorrect config format (Too many chars in room initial)");
				}
				if(!rooms.containsKey(layout.get(i)[j].charAt(0))){
					throw new BadConfigFormatException("Error - Incorrect config format (Incorrect Room initial)");
				}
				if(layout.get(i)[j].length() > 1){
					if(!layout.get(i)[j].matches(".[UDLRN]")){
						throw new BadConfigFormatException("Error - Incorrect config format (Incorrect Door Direction)");
					}
				}
				
				
				if(layout.get(i)[j].charAt(0) == 'W'){
					boardLayout[i][j] = new WalkwayCell(i,j,'W');
				}else if(layout.get(i)[j].length() > 1){
					
					boardLayout[i][j] = new RoomCell(i,j,layout.get(i)[j].charAt(0),layout.get(i)[j].charAt(1));
									
				}else{
					boardLayout[i][j] = new RoomCell(i,j,layout.get(i)[j].charAt(0),'N');

				}
			
			}
		}
	}
	
	public void loadCharacterConfig(){
		
	}
	public void loadWeaponConfig(){
		
	}
	
	//Getters & Setters---------------------------------
	//
	public void setRooms(Map<Character,String> rooms){
		this.rooms = rooms;
	}
	
	public void setConfig(String config){
		this.config = config;
	}
	public BoardCell getCellAt(int row, int col){
		BoardCell cell = boardLayout[row][col];
		
		return cell;
	}
	public RoomCell getRoomCellAt(int row, int col){
		BoardCell cell = boardLayout[row][col];
		if(cell.isRoom()){
			return(RoomCell) cell;
		}
		return null;
	}

	public BoardCell[][] getBoardLayout() {
		return boardLayout;
	}


	public Map<Character, String> getRooms() {
		return rooms;
	}


	public int getNumRows() {
		return numRows;
	}


	public int getNumColumns() {
		return numColumns;
	}

	//ADJACENCY FUNCTIONS-----------------------------------
	//
	//
		
	public void calcAdjacenciesHelp(BoardCell currentCell){
		adjMtx.put(currentCell, new LinkedList<BoardCell>());
		int row = currentCell.getRow();
		int col = currentCell.getColumn();
		
		//If your current cell is a doorway, will only return the walkway in the direction
		//of the door
		if(currentCell.isDoorway()){
			switch(((RoomCell) currentCell).getDoorDirection()){
			case UP :
				adjMtx.get(currentCell).add(boardLayout[currentCell.getRow()-1][currentCell.getColumn()]);
				break;
			case DOWN :
				adjMtx.get(currentCell).add(boardLayout[currentCell.getRow()+1][currentCell.getColumn()]);
				break;
			case LEFT :
				adjMtx.get(currentCell).add(boardLayout[currentCell.getRow()][currentCell.getColumn()-1]);
				break;
			case RIGHT :
				adjMtx.get(currentCell).add(boardLayout[currentCell.getRow()][currentCell.getColumn()+1]);
				break;
			default:
				break;
				
			}
		}else if(!currentCell.isRoom()){
			//checks the cell above currentCell
			if(currentCell.getRow() > 0){
				if(boardLayout[row-1][col].getInitial() == 'W' || 
				  (boardLayout[row-1][col].isDoorway() && ((RoomCell) boardLayout[row-1][col]).getDoorDirection() == DoorDirection.DOWN)){
					
					adjMtx.get(currentCell).add(boardLayout[currentCell.getRow() -1][currentCell.getColumn()]);
				}
			}
			//checks below currentCell
			if((currentCell.getRow()+1) <= (numRows - 1)){
				if(boardLayout[row+1][col].getInitial() == 'W' || 
				  (boardLayout[row+1][col].isDoorway() && ((RoomCell) boardLayout[row+1][col]).getDoorDirection() == DoorDirection.UP)){
					
					adjMtx.get(currentCell).add(boardLayout[currentCell.getRow() +1][currentCell.getColumn()]);
				}
			}
			//checks to the right
			if((currentCell.getColumn()+1) <= (numColumns -1)){
				if(boardLayout[row][col+1].getInitial() == 'W' || 
				  (boardLayout[row][col+1].isDoorway() && ((RoomCell) boardLayout[row][col+1]).getDoorDirection() == DoorDirection.LEFT)){
					
					adjMtx.get(currentCell).add(boardLayout[currentCell.getRow()][currentCell.getColumn() + 1]);
				}
			}
			//checks to the left
			if(currentCell.getColumn() > 0){
				if(boardLayout[row][col-1].getInitial() == 'W' || 
				  (boardLayout[row][col-1].isDoorway() && ((RoomCell) boardLayout[row][col-1]).getDoorDirection() == DoorDirection.RIGHT)){
					adjMtx.get(currentCell).add(boardLayout[currentCell.getRow()][currentCell.getColumn() - 1]);
				}
			}
			
		}
	}
	
	public void calcTargets(int row, int column, int diceRoll){
		BoardCell cell = boardLayout[row][column];
		visited.clear();
		targets.clear();
		visited.add(cell);
		findTargets(cell, diceRoll);
	}
	//Believe this only works for one
	public void findTargets(BoardCell cell, int steps){
		LinkedList<BoardCell> adjacentCells = new LinkedList<BoardCell>(adjMtx.get(cell));
		for(BoardCell x:adjacentCells){
			if(!visited.contains(x)){
				
				visited.add(x);
				if(steps == 1 ||x.isDoorway()){
					targets.add(x);
				}else{
					findTargets(x, steps -1);
				}
				if(!x.isDoorway()){
					visited.remove(x);
				}
			}
			
		}
	}
	
	public LinkedList<BoardCell> getAdjList(int i, int j) {
		return adjMtx.get(getCellAt(i, j));
	}
	
	//fills the Adjacency Matrix
	public void calcAdjacencies(){
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numColumns;j++){
				calcAdjacenciesHelp(getCellAt(i, j));
			}
		}
	}
	public Set<BoardCell> getTargets(){
		return targets;
	}
		
}
