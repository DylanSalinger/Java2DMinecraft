package entities;

import java.awt.Rectangle;

import game.InputHandler;


import gfx.Colors;
import gfx.Screen;
import world.World;

public class Player extends Mob 
{
	private InputHandler input;
	private int color = Colors.get(-1, 111, 145, 543);
	private int scale = 1;
	int xa, ya;
	
	public Player(World world, int x, int y, InputHandler input) 
	{
		super(world, "Steve", x, y, 1);
		this.input = input;
	}

	@Override
	public void tick() 
	{
		xa = 0;
		ya = 0;
		
		if (input.space.isPressed())
		{
			jump();
		}
		if (input.down.isPressed()) //will most likely be removed, just for testing purposes
		{
			ya++;
		}
		if (input.left.isPressed())
		{
			xa--;
		}
		if (input.right.isPressed())
		{
			xa++;
		}
		
		if (!hasCollided(xa, ya) && !input.space.isPressed())
		{
			fall();
		}
		
		if (xa != 0 || ya != 0)
		{
			move(xa, ya);
			isMoving = true;
		}
		else
		{
			isMoving = false;
		}
	}

	@Override
	public void render(Screen screen) 
	{
		int xTile = 0;
		int yTile = 27;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		
		if (movingDir == 1)
		{
			xTile += 2;
		}
		else if (movingDir > 1)
		{
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}
		
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop, scale);
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color, flipBottom, scale);
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom, scale);
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier * 2, xTile + (yTile + 2) * 32, color, flipBottom, scale);
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier * 2, (xTile +1) + (yTile + 2) * 32, color, flipBottom, scale);
	}
	
	@Override
	public boolean hasCollided(int xa, int ya) 
	{
		int xMin = 0;
		int xMax = 7;
		int yMin = 0;
		int yMax = 14;
		for (int x = xMin; x < xMax; x++) //left collision
		{
			if (isSolidTile(xa, ya, x, yMin))
			{
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) //bottom collision
		{
			if (isSolidTile(xa, ya, x, yMax))
			{
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) //top collision
		{
			if (isSolidTile(xa, ya, xMin, y))
			{
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) //right collision
		{
			if (isSolidTile(xa, ya, xMax, y))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void jump() 
	{	
		ya -= dy;
		
	}

	@Override
	public void fall() 
	{
		ya += GRAVITY;
	}
}
