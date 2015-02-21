package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;
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
		room = board.getRoomCellAt(17, 5);
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
	

	//Seen in orange on ClueLayout
	//
	@Test
	public void testAdjacenciesCorner(){
		
				// Test a corner
				LinkedList<BoardCell> testList = board.getAdjList(0, 0);
				Assert.assertEquals(0, testList.size());
				
				
	}
	
	@Test
	public void testAdjacenciesWalkwayUnderneath(){
				// Test one that has walkway underneath
				LinkedList<BoardCell> testList = board.getAdjList(14, 2);
				Assert.assertEquals(0, testList.size());
				
	}
	
	@Test
	public void testAdjacenciesWalkwayAbove(){
				// Test one that has walkway above
				LinkedList<BoardCell> testList = board.getAdjList(15, 12);
				Assert.assertEquals(0, testList.size());
				
	}
	@Test
	public void testAdjacenciesMiddleRoom(){
				// Test one that is in middle of room
				LinkedList<BoardCell> testList = board.getAdjList(2, 9);
				Assert.assertEquals(0, testList.size());
				
	}
	public void testAdjacenciesMiddle(){
				// Test one beside a door
				LinkedList<BoardCell> testList = board.getAdjList(9, 17);
				Assert.assertEquals(0, testList.size());
				
	}
	
		// Ensure that the adjacency list from a doorway is only the
		// walkway. NOTE: This test could be merged with door 
		// direction test. 
		// These tests are PURPLE on the planning spreadsheet
		@Test
		public void testAdjacencyRoomExit()
		{
			// TEST DOORWAY RIGHT 
			LinkedList<BoardCell> testList = board.getAdjList(12, 6);
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.getCellAt(12, 7)));
			// TEST DOORWAY LEFT 
			testList = board.getAdjList(4, 21);
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.getCellAt(4, 20)));
			//TEST DOORWAY DOWN
			testList = board.getAdjList(6, 15);
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.getCellAt(7, 15)));
			//TEST DOORWAY UP
			testList = board.getAdjList(15, 11);
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.getCellAt(14, 11)));
			
		}
		
		// Test adjacency at entrance to rooms
		// These tests are GREEN in planning spreadsheet
		@Test
		public void testAdjacencyDoorways()
		{
			// Test beside a door direction LEFT
			LinkedList<BoardCell> testList = board.getAdjList(4, 19);
			Assert.assertTrue(testList.contains(board.getCellAt(4, 20)));
			Assert.assertTrue(testList.contains(board.getCellAt(4, 18)));
			Assert.assertTrue(testList.contains(board.getCellAt(5, 19)));
			Assert.assertTrue(testList.contains(board.getCellAt(3, 19)));
			Assert.assertEquals(4, testList.size());
			// Test beside a door direction DOWN
			testList = board.getAdjList(7, 15);
			Assert.assertTrue(testList.contains(board.getCellAt(7, 14)));
			Assert.assertTrue(testList.contains(board.getCellAt(6, 15)));
			Assert.assertTrue(testList.contains(board.getCellAt(7, 16)));
			Assert.assertEquals(3, testList.size());
			// Test beside a door direction RIGHT
			testList = board.getAdjList(12, 7);
			Assert.assertTrue(testList.contains(board.getCellAt(12, 6)));
			Assert.assertTrue(testList.contains(board.getCellAt(12, 8)));
			Assert.assertTrue(testList.contains(board.getCellAt(11, 7)));
			Assert.assertTrue(testList.contains(board.getCellAt(13, 7)));
			Assert.assertEquals(4, testList.size());
			// Test beside a door direction UP
			testList = board.getAdjList(14, 11);
			Assert.assertTrue(testList.contains(board.getCellAt(14, 10)));
			Assert.assertTrue(testList.contains(board.getCellAt(14, 12)));
			Assert.assertTrue(testList.contains(board.getCellAt(13, 11)));
			Assert.assertTrue(testList.contains(board.getCellAt(15, 11)));
			Assert.assertEquals(4, testList.size());
		}

		// Test a variety of walkway scenarios
		// These tests are LIGHT PURPLE on the planning spreadsheet
		@Test
		public void testAdjacencyWalkways()
		{
			// Test on top edge of board, just one walkway piece
			LinkedList<BoardCell> testList = board.getAdjList(0, 17);
			Assert.assertTrue(testList.contains(board.getCellAt(0, 18)));
			Assert.assertEquals(1, testList.size());
			
			// Test on left edge of board, three walkway pieces
			testList = board.getAdjList(15, 0);
			Assert.assertTrue(testList.contains(board.getCellAt(14, 0)));
			Assert.assertTrue(testList.contains(board.getCellAt(16, 0)));
			Assert.assertTrue(testList.contains(board.getCellAt(15, 1)));
			Assert.assertEquals(3, testList.size());

			// Test between two rooms, walkways right and left
			testList = board.getAdjList(7, 21);
			Assert.assertTrue(testList.contains(board.getCellAt(7, 20)));
			Assert.assertTrue(testList.contains(board.getCellAt(7, 22)));
			Assert.assertEquals(2, testList.size());

			// Test surrounded by 4 walkways
			testList = board.getAdjList(14,8);
			Assert.assertTrue(testList.contains(board.getCellAt(13, 8)));
			Assert.assertTrue(testList.contains(board.getCellAt(15, 8)));
			Assert.assertTrue(testList.contains(board.getCellAt(14, 7)));
			Assert.assertTrue(testList.contains(board.getCellAt(14, 9)));
			Assert.assertEquals(4, testList.size());
			
			// Test on bottom edge of board, next to 1 room piece
			testList = board.getAdjList(23, 18);
			Assert.assertTrue(testList.contains(board.getCellAt(23, 17)));
			Assert.assertTrue(testList.contains(board.getCellAt(22, 18)));
			Assert.assertEquals(2, testList.size());
			
			// Test on right edge of board, next to 1 room piece
			testList = board.getAdjList(14, 22);
			Assert.assertTrue(testList.contains(board.getCellAt(14, 21)));
			Assert.assertTrue(testList.contains(board.getCellAt(15, 22)));
			Assert.assertEquals(2, testList.size());

			// Test on walkway next to  door that is not in the needed
			// direction to enter
			testList = board.getAdjList(16, 5);
			Assert.assertTrue(testList.contains(board.getCellAt(16, 6)));
			Assert.assertTrue(testList.contains(board.getCellAt(16, 4)));
			Assert.assertTrue(testList.contains(board.getCellAt(15, 5)));
			Assert.assertEquals(3, testList.size());
		}
}
