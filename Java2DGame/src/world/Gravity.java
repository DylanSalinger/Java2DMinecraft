package world;

public interface Gravity 
{
	public final double GRAVITY = 2;
	public final int dy = 5;
	
	public abstract void jump();
	public abstract void fall();
}
