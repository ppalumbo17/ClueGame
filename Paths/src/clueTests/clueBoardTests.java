package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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
	// These tests are LIGHT BLUE on the planning spreadsheet
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
	
	//Test Target Scenarios 
	//These are WHITE on the Planning Spreadsheet
	@Test
	public void TestTargetsOneStep(){
		board.calcTargets(4, 4, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(4, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 4)));	
		
		board.calcTargets(20, 6, 1);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(19, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(21, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(20, 7)));	
	}
	
	//Testing 3 Steps
	public void TestTargetsThreeSteps(){
		board.calcTargets(4, 4, 3);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(2, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(4, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 3)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 4)));
		
		board.calcTargets(20, 6, 3);
		targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(17, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(18, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(19, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(20, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(21, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(22, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(23, 6)));


	}
	
	//Tests for FIVE steps
	@Test
	public void TestTargetsFiveSteps(){
		board.calcTargets(4, 4, 5);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(14, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(2, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(4, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 3)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 2)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(0, 5)));
		
		
		board.calcTargets(20, 6, 5);
		targets= board.getTargets();
		Assert.assertEquals(11, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(16, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(18, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(19, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(20, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(21, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(22, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(23, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(16, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 5)));


	}
	
	//Test for SIX steps
	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(20, 6, 6);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(11, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(16, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 5)));	
		Assert.assertTrue(targets.contains(board.getCellAt(15, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(16, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(16, 8)));	
		Assert.assertTrue(targets.contains(board.getCellAt(17, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(18, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(19, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(21, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(22, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(23, 7)));
	}
	
	//Tests for Getting into a room
	//These tests are WHITE on the planning spreadsheet
	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(16, 16, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		//Down 1, left 1 (into room)
		Assert.assertTrue(targets.contains(board.getCellAt(17, 15)));
		//directly up/down
		Assert.assertTrue(targets.contains(board.getCellAt(14, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(18, 16)));
		//Directly to the right
		Assert.assertTrue(targets.contains(board.getCellAt(16, 18)));
		//Right 1, then up/down 1
		Assert.assertTrue(targets.contains(board.getCellAt(15, 17)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 17)));

	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(16, 16, 5);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(12, targets.size());
		// directly up/down
		Assert.assertTrue(targets.contains(board.getCellAt(13, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(19, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(11, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(21, 16)));
		// directly right
		Assert.assertTrue(targets.contains(board.getCellAt(16, 17)));
		// right then down
		Assert.assertTrue(targets.contains(board.getCellAt(17, 18)));
		Assert.assertTrue(targets.contains(board.getCellAt(18, 17)));
		Assert.assertTrue(targets.contains(board.getCellAt(19, 18)));
		Assert.assertTrue(targets.contains(board.getCellAt(20, 17)));
		// right then up
		Assert.assertTrue(targets.contains(board.getCellAt(14, 17)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 18)));
		Assert.assertTrue(targets.contains(board.getCellAt(14, 19)));
		// up then left
		Assert.assertTrue(targets.contains(board.getCellAt(14, 15)));
		Assert.assertTrue(targets.contains(board.getCellAt(13, 14)));
		// into the rooms
		Assert.assertTrue(targets.contains(board.getCellAt(17, 15)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 19)));			
		
	}
	
	// Test getting out of a room
	// These are WHITE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step
		board.calcTargets(5, 8, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(6, 8)));
		// Take two steps
		board.calcTargets(5, 8, 2);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(6, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 8)));
	}
	//Tests that you only exit out of the same door you entered (two adjacent doors)
	//This is WHITE on the planning spreadsheet
	@Test
	public void testRoomTwoExits()
	{
		// Take one step
		board.calcTargets(12, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(12, 16)));
	}
}
