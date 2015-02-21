package clueGame;

public class WalkwayCell extends BoardCell {

	public WalkwayCell(int row, int column, char Initial) {
		super(row, column, Initial);
		// TODO Auto-generated constructor stub
	}

	public boolean isWalkway(){
		return true;
	}
}
