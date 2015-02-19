package clueGame;

public abstract class BoardCell {

	private int row, column;

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public BoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public boolean isWalkway(){
		return false;
	}
	public boolean isRoom(){
		return false;
	}
	public boolean isDoorway(){
		return false;
	}
}