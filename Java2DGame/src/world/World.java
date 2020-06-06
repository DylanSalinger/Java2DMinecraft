package world;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import game.InputHandler;
import gfx.Screen;
import tiles.Tile;

public class World implements WorldInteraction
{
	private InputHandler input;
	Point coords = new Point();
	private byte[] tiles;
	public int width;
	public int height;
	public List<Entity> entities = new ArrayList<Entity>();
	int terrainMax = 10;
	int terrainMin = 0;
	int terrainHeight = (int) (Math.random() * ((terrainMax - terrainMin) + 1) + terrainMin);
	ArrayList<Rectangle> tileHitBoxes = new ArrayList<Rectangle>();
	
	public World(int width, int height, InputHandler input)
	{
		tiles = new byte[width * height];
		this.width = width;
		this.height = height;
		this.generateWorld();
		this.input = input;
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				tileHitBoxes.add(new Rectangle(x, y, 8, 8));
			}
		}
	}
	
	public void generateWorld()
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				tiles[x + y * width] = Tile.SKY.getId();
				
			}
		}
		for (int x = 0; x < width; x++)
		{
			int prevTerrainHeight = terrainHeight;
			terrainHeight = (int) (Math.random() * ((terrainMax - terrainMin) + 1) + terrainMin);
			if (terrainHeight < prevTerrainHeight)
			{
				terrainHeight = prevTerrainHeight - 1; 
			}
			else if (terrainHeight > prevTerrainHeight)
			{
				terrainHeight = prevTerrainHeight + 1;
			}
			else
			{
				terrainHeight = prevTerrainHeight;
			}
			int y = (height/2 - terrainHeight);
			tiles[x + y * width] = Tile.GRASS.getId();
			for (int underY = y + 1; underY < height; underY++)
			{
				tiles[x + underY * width] = Tile.DIRT.getId();
			}
		}
	}
	
	public void tick()
	{
		for (Entity e : entities)
		{
			e.tick();
		}
	}
	
	public void renderTiles(Screen screen, int xOffset, int yOffset)
	{
		if (xOffset < 0)
		{
			xOffset = 0;
		}
		if (xOffset > ((width << 3) - screen.width))
		{
			xOffset = ((width << 3) - screen.width);
		}
		if (yOffset < 0)
		{
			yOffset = 0;
		}
		if (yOffset > ((height << 3) - screen.height))
		{
			yOffset = ((height << 3) - screen.height);
		}
		
		screen.setOffset(xOffset, yOffset);
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}
		}
		update();
	}
	
	public void renderEntities(Screen screen)
	{
		for (Entity e : entities)
		{
			e.render(screen);
		}
	}

	public Tile getTile(int x, int y) 
	{
		if (0 > x || x >= width || 0 > y || y >= height)
		{
			return Tile.VOID;
		}
		return Tile.tiles[tiles[x + y * width]];
	}
	
	public void addEntity(Entity entity)
	{
		this.entities.add(entity);
	}
	
	public void update()
	{
		blockBroken();
		blockPlaced();
	}

	@Override
	public void blockBroken() 
	{
		if (input.mouse1.isClicked())
		{
			for (int y = 0; y < height; y++)
			{
				for (int x = 0; x < width; x++)
				{
					if (!getTile(x, y).equals(Tile.VOID) && !getTile(x, y).equals(Tile.SKY))
					{
						if(true)
						{
							tiles[x + y * width] = Tile.SKY.getId();
						}
						input.mouse1.setIsClicked(false);
					}
				}
			}
		}
	}

	@Override
	public void blockPlaced() 
	{
		
	}
}
