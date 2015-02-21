package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
	public static final int NUM_ROWS = 24;
	public static final int NUM_COLUMNS = 23;
	
	@BeforeClass
	public static void setUp(){
		ClueGame game = new ClueGame("ClueLayout2.csv", "legend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
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
		RoomCell room = board.getRoomCellAt(1, 4);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(5, 8);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(6, 15);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(4, 20);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(8, 20);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		room = board.getRoomCellAt(11, 6);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(10, 17);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(12, 6);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(11, 17);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(14, 4);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(15, 11);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		room = board.getRoomCellAt(18, 5);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(17, 15);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(17, 19);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
	}
		
	@Test (expected = BadConfigFormatException.class)
	public void testBadRows() throws BadConfigFormatException, FileNotFoundException{
		ClueGame game = new ClueGame("BadRowconfigfile.csv","legend.txt");
		game.loadConfigFiles();
		game.getBoard().loadBoardConfig();
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException{
		ClueGame game = new ClueGame("BadColumnconfigfile.csv","legend.txt");
		game.loadConfigFiles();
		game.getBoard().loadBoardConfig();
	}
		
	@Test (expected = BadConfigFormatException.class)
	public void testBadConfigRoomInitials() throws BadConfigFormatException, FileNotFoundException{
		ClueGame game = new ClueGame("BadRoomInitialsconfigfile.csv","legend.txt");
		game.loadConfigFiles();
		game.getBoard().loadBoardConfig();
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testBadConfig() throws BadConfigFormatException, FileNotFoundException{
		ClueGame game = new ClueGame("NotAFile.csv","legend.txt");
		game.loadConfigFiles();
		game.getBoard().loadBoardConfig();
	}
	

}
