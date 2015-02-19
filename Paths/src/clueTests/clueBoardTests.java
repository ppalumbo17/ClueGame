package clueTests;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;
import clueGame.RoomCell;

import org.junit.Test;

public class clueBoardTests {
	private static Board board;
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;
	
	@Before
	public void setUp(){
		ClueGame game = new ClueGame();
	}
	/*@Test
	public void testRooms() {
		fail("Not yet implemented");
	}*/
	@Test
	public void testRoomNumber(){
		Map<Character, String> rooms = board.getRooms();
		Assert.assertEquals(NUM_ROOMS, rooms.size());
		
	
	}
	@Test
	public void testRoomInitials(){
		Map<Character, String> rooms = board.getRooms();
		Assert.assertEquals("Conservatory", rooms.get('C'));
		Assert.assertEquals("Ballroom", rooms.get('B'));
		Assert.assertEquals("Study", rooms.get('S'));
		Assert.assertEquals("Hall", rooms.get('H'));
		Assert.assertEquals("TV Room", rooms.get('T'));
		Assert.assertEquals("Dining Room", rooms.get('D'));
		Assert.assertEquals("Pool Room", rooms.get('P'));
		Assert.assertEquals("Kitchen", rooms.get('K'));
		Assert.assertEquals("Walkway", rooms.get('W'));
		Assert.assertEquals("Closet", rooms.get('X'));
	}
	
	@Test
	public void testBoardDimensions(){
		Assert.assertEquals(NUM_ROWS, board.getNumRows());
		Assert.assertEquals(NUM_COLUMNS, board.getNumColumns());
		
	}
	
	@Test
	public void testDoorDirections(){
		RoomCell room = board.getRoomCellAt(2,5);
	}
	

}
