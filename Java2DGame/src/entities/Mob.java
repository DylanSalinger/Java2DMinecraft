package entities;

import java.awt.Rectangle;

import tiles.Tile;
import world.Gravity;
import world.World;

public abstract class Mob extends Entity implements Gravity
{
	protected String name;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int scale = 1;
	
	public Mob(World world, String name, int x, int y, int speed) 
	{
		super(world);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void move(int xa, int ya)
	{
		if (xa != 0 && ya != 0)
		{
			move(xa, 0);
			move(0, ya);
			numSteps--;
			return;
		}
		numSteps++;
		if (!hasCollided(xa, ya)) 
		{
            if (ya < 0)
            {
                movingDir = 0;
            }
            if (ya > 0)
            {
            	  movingDir = 1;
            }
            if (xa < 0)
            {
            	 movingDir = 2;
            }
            if (xa > 0)
            {
            	  movingDir = 3;
            }
            x += xa * speed;
            y += ya * speed;
        }
	}

	public abstract boolean hasCollided(int xa, int ya);
	
	protected boolean isSolidTile(int xa, int ya, int x, int y)
	{
		if (world == null)
		{
			return false;
		}
		Tile lastTile = world.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = world.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3); 
		if (!lastTile.equals(newTile) && newTile.isSolid())
		{
			return true;
		}
		return false;
	}
	
	public String getName()
	{
		return name;
	}
	
    public int getNumSteps() 
    {
        return numSteps;
    }

    public boolean isMoving() 
    {
        return isMoving;
    }

    public int getMovingDir() 
    {
        return movingDir;
    }

    public void setNumSteps(int numSteps) 
    {
        this.numSteps = numSteps;
    }

    public void setMoving(boolean isMoving) 
    {
        this.isMoving = isMoving;
    }

    public void setMovingDir(int movingDir) 
    {
        this.movingDir = movingDir;
    }
}
