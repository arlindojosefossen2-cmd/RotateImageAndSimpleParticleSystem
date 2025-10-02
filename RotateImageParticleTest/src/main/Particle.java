package main;

import java.awt.Color;
import java.awt.Graphics2D;

public final class Particle
{
	private Color color;
	
	private int size;
	
	private int maxLife;
	private int life;
	
	private int speed;
	
	private int xd;
	private int yd;
	private int xPos;
	private int yPos;
	
	boolean alive;
	
	private Vector2F vec;
	
	public Particle(Vector2F vec,Color color, int size, int maxLife, int speed, int xd, int yd,int tilesize)
	{
		this.vec = vec;
		this.color = color;
		this.size = size;
		this.maxLife = maxLife;
		this.speed = speed;
		this.xd = xd;
		this.yd = yd;
		this.life = maxLife;
		this.alive = true;
		
		int offSet = (tilesize / 2) - (size / 2);
		xPos = xPos + offSet;
		yPos = yPos + offSet;
	}
	
	public void update()
	{
		life -= 1;
		
		if(life < maxLife/3)
		{
			yd++;
		}
		
		xPos = xPos + (xd*speed);
		yPos = yPos + (yd*speed);
		
		if(life == 0)
		{
			this.alive  = false;
		}
	}
	
	public void draw(Graphics2D graphics2d)
	{
		int screenX = (int) (xPos + vec.getX());
		int screenY = (int) (yPos + vec.getY());
		
		graphics2d.setColor(color);
		graphics2d.fillRoundRect(screenX, screenY, size, size,16,16);
	}
}