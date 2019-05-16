package empires;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entities.Handler;

public class KeyInput extends KeyAdapter
{
	Handler handler;
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_1)
		{
			handler.tribe1 =! handler.tribe1;
			handler.tribe2 = false;
			handler.tribe3 = false;
			handler.tribe4 = false;
		} else if (key == KeyEvent.VK_2)
		{
			handler.tribe2 =! handler.tribe2;
			handler.tribe1 = false;
			handler.tribe3 = false;
			handler.tribe4 = false;
		} else if (key == KeyEvent.VK_3)
		{
			handler.tribe3 =! handler.tribe3;
			handler.tribe1 = false;
			handler.tribe2 = false;
			handler.tribe4 = false;
		} else if (key == KeyEvent.VK_4)
		{
			handler.tribe4 =! handler.tribe4;
			handler.tribe1 = false;
			handler.tribe2 = false;
			handler.tribe3 = false;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();	
	}
}
