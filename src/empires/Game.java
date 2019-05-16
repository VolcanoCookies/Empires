package empires;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import entities.Colony;

public class Game extends Canvas implements Runnable
{
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	
	private Camera camera;
	
	static int lastUpdates, lastFrames;
	
	static int mouseX = 0, mouseY = 0;
	
	public Game(){
		
		new Init();
		handler = new Handler(Init.DEFAULT_WIDTH, Init.DEFAULT_HEIGHT);
		
		camera = new Camera(0, 0);
		
		new Window(Init.DEFAULT_WIDTH, Init.DEFAULT_HEIGHT + 29, "Empires", this);
		
		new Colony(5000, 10000, 1000, Color.red, 0, 300, 300, handler);
		
		start();
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop()
	{
		running = false;
		try {
			thread.join();
			System.out.println("Game thread stopped");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void run()
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				lastUpdates = updates;
				lastFrames = frames;
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public void tick()
	{
		handler.tick();
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		////////////////////////////////////////
		g.drawImage(Init.mapImage, 0, 0, null);
		handler.render(g);
		g.setFont(new Font("Georgia", 12, 12));
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + lastFrames + " Ticks: " + lastUpdates, 3, 12);
		g.drawString("MouseX: " + mouseX, 3, 24);
		g.drawString("MouseY: " + mouseY, 3, 36);
		
		////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
}
