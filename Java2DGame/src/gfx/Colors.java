package gfx;

public class Colors 
{
	//Color 1 (R:0,G:0,B:0); Color 2 (R:85,G:85,B:85); Color 3(R:170,G:170,B:170); Color 4 (R:255,G:255,B:255) **SPRITE SHEET**
	public static int get(int color1, int color2, int color3, int color4) //black, dark gray, light gray, white
	{
		return (get(color4) << 24) + (get(color3) << 16) + (get(color2) << 8) + (get(color1));
	}
	
	/*
	 * Colors are returned based on method in game class. Color 1, 2, 3 and 4 refer to the shade of the color.
	 * When inputing your 3-digit color code (R, G, B), keep in mind this formula:
	 * RGB value = x * (255/5)
	 * x = the value you put in.
	 * So, to get a pure white value, x must = 5 for RGB, because 5 * (255/5) = 255 (255,255,255) = white
	 */
	
	private static int get(int color)
	{
		if (color < 0)
		{
			return 255;
		}
		int r = color/100%10;
		int g = color/10%10;
		int b = color%10;
		return r * 36 + g * 6 + b;
	}
}
