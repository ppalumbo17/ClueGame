package clueGame;
import java.util.*;


public class Board {

	private BoardCell[][] boardLayout;
	private Map<Character, String> rooms;
	private int numRows, numColumns;
	
	
	public void loadBoardConfig(){
		
	}
	public BoardCell getRoomCellAt(int row, int col){
		return boardLayout[row][col];
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
