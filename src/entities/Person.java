package entities;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import empires.Handler;

public class Person extends GameObject
{
	protected int dir;
	private Handler handler;
	
	protected int age = 1;
	protected int strenght;
	protected Colony colony;
	
	protected final int REPRODUCTION_AGE;
	protected boolean shouldReproduce = false;
	
	public Person(int x, int y, int strenght, Handler handler, Colony colony) 
	{
		
		super(x, y, strenght);
		this.handler = handler;
		this.strenght = strenght;
		this.colony = colony;
		this.REPRODUCTION_AGE = Math.max(Math.round(strenght/2), 1000);
		
	}

	public void tick() 
	{
		
		//Death
		if(this.age>this.strenght) {
			handler.removeObject(this);
		}
		
		//If person can reproduce
		if(age%REPRODUCTION_AGE==0) shouldReproduce = true;
		
		//Directions
		//	0 1 2
		//	3 ¤ 4
		//	5 6 7
		int oldX = this.x;
		int oldY = this.y;
		switch (new Random().nextInt(7)) {
		case 0:
			this.x--;
			this.y--;
			break;
		case 1:
			this.y--;
			break;
		case 2:
			this.x++;
			this.y--;
			break;
		case 3:
			this.x--;
			break;
		case 4:
			this.x++;
			break;
		case 5:
			this.x--;
			this.y++;
			break;
		case 6:
			this.y++;
			break;
		case 7:
			this.x++;
			this.y++;
			break;
		}
		//If a person collided this turn they loose the chance to reproduce.
		boolean collided = false;
		List<GameObject> list = handler.getNearbyEntities(this.x, this.y);
		for(int i = 0; i < list.size(); i++) {
			Person person = (Person) list.get(i);
			
			if(person.x==this.x && person.y == person.x) {
				
				collided = true;
				
				if(person.colony.equals(this.colony)) {
					//Same colony
					
					this.x = oldX;
					this.y = oldY;
					
				} else {
					//Enemies
					
					
					
				}
				
			}
			
		}
		
		//Reproducing
		if(!collided && shouldReproduce) {
			handler.addObject(new Person(oldX, oldY, Math.round(this.strenght * ((new Random().nextInt(10) + 95) / 100)), handler, this.colony));
		}
		
		this.age++;
		
	}
	
	public void render(Graphics g) 
	{
		g.setColor(this.colony.color);
		g.drawRect(x, y, 1, 1);
	}
	
}
