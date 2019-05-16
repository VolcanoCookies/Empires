package entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import empires.Handler;

public class Colony {
	
	protected Color color;
	protected int strenghtMax;
	protected int strenghtMin;
	protected int startIndividuals;
	protected int ID;
	protected int startX, startY;
	
	public Colony(int startIndividuals, int strenghtMax, int strenghtMin, Color color, int ID, int startX, int startY, Handler handler) {
		
		this.startIndividuals = startIndividuals;
		this.strenghtMax = strenghtMax;
		this.strenghtMin = strenghtMin;
		this.color = color;
		this.ID = ID;
		this.startX = startX;
		this.startY = startY;
		
		//Initiate the colony
		int startAreaRadius = (int) (Math.sqrt(startIndividuals/Math.PI) * 2);
		System.out.println("Colony " + this.ID + " radius: " + startAreaRadius);
		List<String> locations = new ArrayList<>();
		for(int i = 0; i < startIndividuals; i++) {
			
			int startingX, startingY;
			int tries = 0;
			
			do {
				tries++;
				startingX = new Random().nextInt(startAreaRadius * 2) - startAreaRadius;
				startingY = new Random().nextInt(startAreaRadius * 2) - startAreaRadius;
				
				if(tries > 10) break;
				
			} while(locations.contains(new String(startingX + ";" + startingY)) || Math.sqrt(startingX * startingX + startingY * startingY) > startAreaRadius);
			
			if(tries < 10) {

				locations.add(startingX + ";" + startingY);
				
				handler.addObject(new Person(startingX + startX, startingY+ startY, new Random().nextInt(strenghtMax - strenghtMin - 1) + strenghtMin + 1, handler, this));	
			}
			
		}
		
	}
	
	public Color getColor() {
		return color;
	}
	
}
