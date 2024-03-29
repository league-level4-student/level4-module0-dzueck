package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Graphics;

public class Cell implements Drawable{
	public boolean isAlive = false;
	
	private int x;
	private int y;

	private int cellSize;
	
	
	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.cellSize = size;
	}
	
	//11. Complete tue liveOrDie method
	//    It sets isAlive to true or false based on the neighbors and 
	//the rules of the game
	/*
	 * 1. Any live cell with fewer than two live nieghbours dies, as if caused by underpopulation.
	 * 2. Any live cell with two or three live neighbours lives on to the next generation.
	 * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
	 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 * (source: Wikipedia)
	 * */
	public void liveOrDie(int numNeighbors) {
		if(isAlive) {
			if(numNeighbors != 2 && numNeighbors != 3) {
				isAlive = false;
			}
		}
		else {
			if(numNeighbors == 3) {
				isAlive = true;
			}
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	//12. Complete the draw method.
	//    It draws a colored square if cell is alive
	//    draws empty square if cell is dead
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(100,100,100));
		g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
		if(!isAlive) {
			g.setColor(Color.BLACK);
			g.drawRect((x * cellSize) + 1, (y * cellSize) + 1, cellSize - 2, cellSize - 2);
			g.fillRect((x * cellSize) + 1, (y * cellSize) + 1, cellSize - 2, cellSize - 2);
		}
		else {
			g.setColor(Color.WHITE);
			g.drawRect((x * cellSize) + 1, (y * cellSize) + 1, cellSize - 2, cellSize - 2);
			g.fillRect((x * cellSize) + 1, (y * cellSize) + 1, cellSize - 2, cellSize - 2);
		}
		
		
		
		
		
	}
}
