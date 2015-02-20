package clueGame;

public class RoomCell extends BoardCell{

	public enum DoorDirection {UP, DOWN, RIGHT, LEFT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public RoomCell(int row, int column) {
		super(row, column);
		// TODO Auto-generated constructor stub
	}

	public boolean isRoom(){
		return true;
	}
	public DoorDirection getDoorDirection(){
		return doorDirection;
	}

	
}
