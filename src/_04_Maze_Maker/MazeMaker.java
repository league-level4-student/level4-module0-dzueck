package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Random random = new Random();
		
		maze.getCell(0, random.nextInt(h)).setWestWall(false);
		maze.getCell(w - 1, random.nextInt(h)).setEastWall(false);
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(random.nextInt(w), random.nextInt(h)));
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		Random random = new Random();
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisited = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisited.size() > 0) {
			//C1. select one at random.
			Cell thing = unvisited.get(random.nextInt(unvisited.size()));
			//C2. push it to the stack
			uncheckedCells.push(thing);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, thing);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = thing;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		else {
			
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			if(uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack
				
				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
			
		}
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(Math.abs(c1.getX() - c2.getX()) == 1 && c1.getY() - c2.getY() == 0) {
			if(c1.getX() > c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
			else {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
		else if(Math.abs(c1.getY() - c2.getY()) == 1 && c1.getX() - c2.getX() == 0) {
			if(c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
			else {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> uVNeighbors = new ArrayList<>();
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(!(i == 0 && j == 0) && (i == 0 || j == 0)) {
					try{
						if(!maze.getCell(c.getX() + i, c.getY() + j).hasBeenVisited()) {
							uVNeighbors.add(maze.getCell(c.getX() + i, c.getY() + j));
						}
					}
					catch(Exception e) {
					}
				}
			}
		}
		return uVNeighbors;
		

	}
}
