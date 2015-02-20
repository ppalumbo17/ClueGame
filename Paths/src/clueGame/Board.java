package clueGame;
import java.util.*;


public class Board {
	private static int MAX_CELLS = 50;
	private BoardCell[][] boardLayout = new BoardCell[MAX_CELLS][MAX_CELLS];
	private Map<Character, String> rooms;
	private int numRows, numColumns;
	
	
	public void loadBoardConfig(){
		
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
	
}
