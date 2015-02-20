package clueGame;
import java.util.*;
public class ClueGame {

	private Map<Character, String> rooms;
	private Board gameboard;
	private String config;
	private String legend;
	
	public ClueGame(String config, String legend){
		this.config = config;
		this.legend = legend;
	}
	public void loadConfigFiles(){
		
	}
	
	public Board getBoard(){
		return gameboard;
	}
}
