package clueGame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;
public class ClueGame {

	private Map<Character, String> rooms;
	private Board gameboard;
	private String config;
	private String legend;
	
	public ClueGame(String config, String legend){
		this.config = config;
		this.legend = legend;
		rooms = new HashMap<Character,String>();
		gameboard = new Board();
		
	}
	public ClueGame() {
		config = "ClueLayout.csv";
		legend = "ClueLegend.txt";
		gameboard = new Board();
	}
	public void loadConfigFiles(){
		gameboard.setConfig(config);
		//gameboard.setRooms(rooms);
		try{
			gameboard.loadBoardConfig();
		}catch(BadConfigFormatException e){
			System.out.println(e.getLocalizedMessage());
		}
		
		//gameboard.loadCharacterConfig();
		//gameboard.loadWeaponConfig();
	}
	
	public void loadRoomConfig()throws BadConfigFormatException{
		FileReader reader = null;
		Scanner in = null;
		try{
			reader = new FileReader(legend);
			in = new Scanner(reader);
		}catch (FileNotFoundException e){
			System.out.println(e.getLocalizedMessage());
		}
		String[] buf;
		char roomInitial;
		String roomName;
		
		while(in.hasNextLine()){
			buf = in.nextLine().split(", ");
			if(buf.length == 2){
				roomInitial = buf[0].charAt(0);
				roomName = buf[1];
				rooms.put(roomInitial, roomName);
			}else{
				throw new BadConfigFormatException("Legend Format Incorrect");
			}
		}
		
		in.close();
		
		
		
	}
	
	public Board getBoard(){
		return gameboard;
	}
}
