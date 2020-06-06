package tiles;

import gfx.Screen;
import world.World;

public class BasicTile extends Tile
{
	protected int tileId;
	protected int tileColor;
	
	
	public BasicTile(int id, int x, int y, int tileColor) 
	{
		super(id, false, false);	
		this.tileId = x + y;
		this.tileColor = tileColor;
	}

	@Override
	public void render(Screen screen, World world, int x, int y) 
	{
		screen.render(x, y, tileId, tileColor, 0x00, 1);
	}
}
