package empires;

import java.util.ArrayList;
import java.util.List;

import entities.GameObject;

public class Chunk {
	
	protected int x,y;
	public List<GameObject> entities = new ArrayList<GameObject>();
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public List<GameObject> getEntities() {
		return entities;
	}
	
	public void addEntity(GameObject object) {
		entities.add(object);
	}
	
	public void removeEntity(GameObject object) {
		entities.remove(object);
	}
	
	
}
