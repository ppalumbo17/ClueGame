package clueGame;

public class BadConfigFormatException extends Exception{

	public BadConfigFormatException(String message){
		super(message);
	}
	
	public String toString(){
		return "Bad Config File";
	}
}
