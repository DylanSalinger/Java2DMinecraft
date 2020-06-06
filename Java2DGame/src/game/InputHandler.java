package game;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener
{
	public InputHandler(Game game)
	{
		game.addKeyListener(this);
		game.addMouseListener(this);
	}
	
	public class Key
	{
		public int numTimesPressed = 0;
		private boolean pressed = false;
		
		public int getNumTimesPressed()
		{
			return numTimesPressed;
		}
		
		public boolean isPressed()
		{
			return pressed;
		}
		
		public void toggle(boolean isPressed)
		{
			pressed = isPressed;
			if (isPressed)
			{
				numTimesPressed++;
			}
		}
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key space = new Key();

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		toggleKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		toggleKey(e.getKeyCode(), false);
	}
	
	public void toggleKey(int keyCode, boolean isPressed)
	{
		if(keyCode == KeyEvent.VK_SPACE)
		{
			space.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)
		{
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
		{
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
		{
			right.toggle(isPressed);
		}
	}

	public class Mouse
	{	
		public boolean isClicked = false;
		
		public Point toggle()
		{
			return MouseInfo.getPointerInfo().getLocation();
		}
		
		public boolean isClicked()
		{
			return isClicked;
		}
		
		public void setIsClicked(boolean isClicked)
		{
			this.isClicked = isClicked;
		}
		
		public int getX()
		{
			return mouse1.getX();
		}
		
		public int getY()
		{
			return mouse1.getY();
		}
	}
	
	public Mouse mouse1 = new Mouse();
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		toggleMouse(e.getButton(), true);
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		
	}
	
	public void toggleMouse(int mouseCode, boolean isClicked)
	{
		if (mouseCode == MouseEvent.BUTTON1)
		{
			mouse1.toggle();
			mouse1.setIsClicked(isClicked);
			mouse1.isClicked();
		}
	}
}
