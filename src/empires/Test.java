package empires;

import java.security.SecureRandom;

public class Test 
{
	public static void main(String[] args)
	{
		int dir;
		int x = 0, y = 0;
		for(int i = 0; i < 5000; i++)
		{
			dir = new SecureRandom().nextInt(999);
			if (dir <= 249)
			{
				y++;
			} else if (dir <= 499 && dir > 249)
			{
				x++;
			} else if (dir <= 749 && dir > 499)
			{
				y--;
			} else if (dir <= 999 && dir > 749)
			{
				x--;
			}
		}
		System.out.println("X: " + x + " Y: " + y);
	}
}
