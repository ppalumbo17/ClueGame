package clueGame;

public abstract class BoardCell {

	private int row, column;
	private char initial;

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public char getInitial(){
		return this.initial;
	}

	public BoardCell(int row, int column, char Initial) {
		super();
		this.row = row;
		this.column = column;
		this.initial = Initial;
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