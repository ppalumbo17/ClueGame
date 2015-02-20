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
		gameboard = new Board();
		
	}
	public void loadConfigFiles(){
		gameboard.loadBoardConfig();
		gameboard.loadCharacterConfig();
		gameboard.loadWeaponConfig();
	}
	
	public Board getBoard(){
		return gameboard;
	}
}
