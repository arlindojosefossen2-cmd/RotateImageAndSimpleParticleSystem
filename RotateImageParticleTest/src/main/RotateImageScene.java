package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import br.com.ajf.game.input.container.GameInputContainer;
import br.com.ajf.game.scene.Scene;

public final class RotateImageScene implements Scene
{
	private static final int TILESIZE = 64;
	
	private BufferedImage image = new BufferedImage(TILESIZE, TILESIZE, BufferedImage.TYPE_INT_ARGB);
	private AffineTransform transform;
	
	private float angle;
	private float rot = 0.1F;

	private Vector2F position = new Vector2F(320, 320);
	private Vector2F velocity = new Vector2F();
	private Vector2F acceleration = new Vector2F();
	private Vector2F heading = new Vector2F(0, 1);

	private float max = 7.0F;
	
	private List<Particle> particles = new ArrayList<Particle>();
	
	
	@Override
	public void start()
	{
		Graphics2D g = image.createGraphics();	
		g.setColor(Color.red);
		g.fillRect(0, 0, TILESIZE, TILESIZE);	
		g.dispose();
	}

	@Override
	public void update()
	{
		if(GameInputContainer.keys[KeyEvent.VK_A])
		{
			angle -= rot;
		}
		else if(GameInputContainer.keys[KeyEvent.VK_D])
		{
			angle += rot;
		}
		
		if(GameInputContainer.keys[KeyEvent.VK_W])
		{
			acceleration = heading.normalize().scale(0.2f);	
		}
		else
		{
			if(velocity.magnitud() != 0)
			{
				acceleration = (velocity.scale(-1).normalize()).scale(0.2f);
			}
		}
		
		velocity.add(acceleration);
		velocity.limit(max);
		heading.direction((float)(angle - Math.PI/2));	
		position.add(velocity);
		
		if(GameInputContainer.keys[KeyEvent.VK_V])
		{
			particles.add(new Particle(position, Color.BLUE, 8, 16, 1, -2, -1, TILESIZE));
			particles.add(new Particle(position, Color.green, 8, 16, 1, 2, -1, TILESIZE));
			particles.add(new Particle(position, Color.YELLOW, 8, 16, 1, -2, 1, TILESIZE));
			particles.add(new Particle(position, Color.ORANGE, 8, 16, 1, 2, 1, TILESIZE));
		}
		
		for (int i = 0; i < particles.size(); i++)
		{
			particles.get(i).update();
		
			if(!particles.get(i).alive)
			{
				particles.remove(i);
			}
		}

	}

	@Override
	public void draw(Graphics2D graphics2d)
	{
	
		transform = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		transform.rotate(angle,TILESIZE/2,TILESIZE/2);
		
		if(image != null)
			graphics2d.drawImage(image,transform, null);
		
		for (int i = 0; i < particles.size(); i++)
		{
			if(particles.get(i) != null && particles.get(i).alive)
			{
				particles.get(i).draw(graphics2d);
			}
		}
	
	
	}

}