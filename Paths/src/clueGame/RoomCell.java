package clueGame;

public class RoomCell extends BoardCell{

	public enum DoorDirection {UP, DOWN, RIGHT, LEFT, NONE};
	private DoorDirection doorDirection;
	//private char roomInitial;s
	
	public RoomCell(int row, int column, char Initial, char direction) {
		super(row, column, Initial);

		switch(direction){
		case('U'):
			doorDirection = DoorDirection.UP;
		case('D'):
			doorDirection = DoorDirection.DOWN;
		case('R'):
			doorDirection = DoorDirection.RIGHT;
		case('L'):
			doorDirection = DoorDirection.LEFT;
		case('N'):
			doorDirection = DoorDirection.NONE;
		}
	}

	public boolean isRoom(){
		return true;
	}
	public DoorDirection getDoorDirection(){
		return doorDirection;
	}
	
	public boolean isDoor(){
		if(doorDirection != DoorDirection.NONE){
			return true;
		}else{
			return false;
		}
	}

	
}
