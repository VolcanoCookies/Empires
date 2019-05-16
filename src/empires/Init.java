package empires;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Init 
{
	public static BufferedImage icon = null;
	public static BufferedImage mapImage = null;
	public static BufferedImage pallet = null;
	public static BufferedImage town = null;
	public Graphics g;
	
	static int DEFAULT_HEIGHT;
	static int DEFAULT_WIDTH;
	
	public static int[][] map;
	public static Color land;
	public static Color water;
	public Init()
	{
		try {
			icon = ImageIO.read(new File("E:\\GitHub\\Empires\\res\\icon.png"));
			pallet = ImageIO.read(new File("E:\\GitHub\\Empires\\res\\pallet.png"));
			mapImage = ImageIO.read(new File("E:\\GitHub\\Empires\\res\\Map_InProgress.png"));
		} catch (IOException e) {
			System.out.println("Error e");
		}
		//Setting a few constants for Empire Class.
		land = new Color(pallet.getRGB(1, 0));
		water = new Color(pallet.getRGB(1, 1));
		DEFAULT_HEIGHT = mapImage.getHeight();
		DEFAULT_WIDTH = mapImage.getWidth();
		
		map = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT];
		
		for (int x = 0; x < mapImage.getWidth(); x++)
		{
			for (int y = 0; y < mapImage.getHeight(); y++)
			{
				if (mapImage.getRGB(x, y) == pallet.getRGB(0, 0))
				{
					//1 = land
					map[x][y] = 1;
				} else if (mapImage.getRGB(x, y) == pallet.getRGB(0, 1))
				{
					//2 = water
					map[x][y] = 2;
				}
			}
		}
	}	
}