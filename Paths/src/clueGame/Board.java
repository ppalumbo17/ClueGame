package clueGame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import clueGame.RoomCell.DoorDirection;


public class Board {
	private static int MAX_CELLS = 50;
	private BoardCell[][] boardLayout;
	private Map<Character, String> rooms;
	private String config;
	private int numRows;
	private int numColumns;
	
	public Board(){
		
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
	
}
