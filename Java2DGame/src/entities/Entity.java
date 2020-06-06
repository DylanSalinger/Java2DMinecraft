package entities;

import gfx.Screen;
import world.World;

public abstract class Entity 
{
	public int x, y;
	protected World world;
	
	public Entity(World world)
	{
		init(world);
	}
	
	public final void init(World world)
	{
		this.world = world;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen);
}
