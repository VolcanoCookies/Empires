package entities;

import java.awt.Graphics;

public abstract class GameObject 
{
	protected int x, y, c, s;
	protected ID id;
	
	public GameObject(int x, int y, int s)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}
	

	public ID getId() {
		return id;
	}

	
	public void setId(ID id) {
		this.id = id;
	}
	
	
}
