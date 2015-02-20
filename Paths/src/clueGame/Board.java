package clueGame;
import java.util.*;


public class Board {
	private static int MAX_CELLS = 50;
	private BoardCell[][] boardLayout = new BoardCell[MAX_CELLS][MAX_CELLS];
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	public Board(){
		//PLACEHOLDER - FOR TESTS ----------------------
		//
		numRows = MAX_CELLS;
		numColumns = MAX_CELLS;
		for (int i = 0; i< numRows;i++){
			for (int j = 0;j<numColumns;j++){
				boardLayout[i][j] = new RoomCell(i,j);
			}
		}
		//PLACEHOLDER - FOR TESTS -----------------------
		//
	}
	public void loadBoardConfig(){
		
	}
	public void loadCharacterConfig(){
		
	}
	public void loadWeaponConfig(){
		
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
		return new HashMap<Character,String>();
	}


	public int getNumRows() {
		return numRows;
	}


	public int getNumColumns() {
		return numColumns;
	}
	
}
