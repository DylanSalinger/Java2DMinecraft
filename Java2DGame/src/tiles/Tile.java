package tiles;

import gfx.Colors;
import gfx.Screen;
import world.World;

public abstract class Tile 
{
	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colors.get(000, -1, -1, -1));
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colors.get(-1, 333, -1, -1)); 
	public static final Tile GRASS = new BasicSolidTile(2, 2, 0, Colors.get(110, 131, 141, 221));
	public static final Tile SKY = new BasicTile(3, 3, 0, Colors.get(235, -1, -1, -1));
	public static final Tile DIRT = new BasicSolidTile(4, 4, 0, Colors.get(-1, 110, 221, -1));
	

	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	
	public Tile(int id, boolean isSolid, boolean isEmitter)
	{
		this.id = (byte) id;
		if (tiles[id] != null)
		{
			throw new RuntimeException("Duplicate tile id on " + id);
		}
		this.solid = isSolid;
		this.emitter = isEmitter;
		tiles[id] = this;
	}
	
	public byte getId()
	{
		return id;
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public boolean isEmitter()
	{
		return emitter;
	}
	
	public abstract void render(Screen screen, World world, int x, int y);

}
