package clueGame;

public class RoomCell extends BoardCell{

	private DoorDirection doorDirection;
	private char roomInitial;
	
	public RoomCell(int row, int column) {
		super(row, column);
		// TODO Auto-generated constructor stub
	}
	public boolean isRoom(){
		return true;
	}

	
}
