package empires;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.GameObject;

public class Handler 
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public List<Chunk> chunks = new ArrayList<Chunk>();
	
	public Handler(int worldWidth, int worldHeight) {
		for(int y = 0; y < worldWidth + 3; y++) {
			for(int x = 0; x < worldHeight + 3; x++) {
				chunks.add(new Chunk(x, y));
			}
		}
	}
	
	public void tick()
	{
		chunks.forEach(c -> c.entities.clear());
		List<GameObject> list = object;
		for(int i = 0; i < list.size(); i++) {
			GameObject object = list.get(i);
			chunks.get(object.getX()*object.getY()).addEntity(object);
			object.tick();
		}
	}
	public void render(Graphics g)
	{
		List<GameObject> list = object;
		for(int i = 0; i < list.size(); i++) {
			list.get(i).render(g);
		}
	}
	
	public void addObject(GameObject tempObject)
	{
		object.add(tempObject);
	}
	public void removeObject(GameObject tempObject)
	{
		object.remove(tempObject);
	}
	public LinkedList<GameObject> getObject() {
		return object;
	}
	
	public List<Chunk> getNearbyChunks(int x, int y) {
		List<Chunk> chunks = new ArrayList<>();
		
		int chunkX = x/3;
		int chunkY = y/3;
		
		chunks.add(this.chunks.get(chunkX*chunkY));
		chunks.add(this.chunks.get(chunkX*chunkY-1));
		chunks.add(this.chunks.get(chunkX*chunkY+1));
		chunks.add(this.chunks.get(chunkX*chunkY-1+chunkY));
		chunks.add(this.chunks.get(chunkX*chunkY+1-chunkY));
		chunks.add(this.chunks.get(chunkX*chunkY-1-chunkY));
		chunks.add(this.chunks.get(chunkX*chunkY+1+chunkY));
		chunks.add(this.chunks.get(chunkX*chunkY+chunkY));
		chunks.add(this.chunks.get(chunkX*chunkY-chunkY));
		
		return chunks;
	}
	
	public List<GameObject> getNearbyEntities(int x, int y) {
		List<GameObject> entities = new ArrayList<>();
		
		getNearbyChunks(x, y).forEach(c -> entities.addAll(c.entities));
		
		return entities;
	}
}
