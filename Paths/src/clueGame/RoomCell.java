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
			break;
		case('D'):
			doorDirection = DoorDirection.DOWN;
			break;
		case('R'):
			doorDirection = DoorDirection.RIGHT;
			break;
		case('L'):
			doorDirection = DoorDirection.LEFT;
			break;
		case('N'):
			doorDirection = DoorDirection.NONE;
			break;
		}
	}

	public boolean isRoom(){
		return true;
	}
	@Override
	public String toString() {
		return "RoomCell [doorDirection=" + doorDirection + "]";
	}

	public DoorDirection getDoorDirection(){
		return doorDirection;
	}
	
	public boolean isDoorway(){
		if(doorDirection != DoorDirection.NONE){
			return true;
		}else{
			return false;
		}
	}

	
}
