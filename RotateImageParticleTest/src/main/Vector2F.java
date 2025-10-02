package main;

public final class Vector2F
{
	private float x,y;

	public Vector2F()
	{
		x = 0;
		y = 0;
	}

	public Vector2F(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2F add(Vector2F vec)
	{
		x = x + vec.getX();
		y = y + vec.getY();
		return this;
	}
	
	public Vector2F subtract(Vector2F vec)
	{
		x = x - vec.getX();
		y = y - vec.getY();
		return this;
	}
	
	public Vector2F scale(float scale)
	{
		x = x*scale;
		y = y*scale;
		return this;
	}
	
	public Vector2F limit(float value)
	{
		return (magnitud() > value) ? this.normalize().scale(value) : this;
	}
	
	public Vector2F normalize()
	{
		float mag = magnitud();
		
		x = x/mag;
		y = y/mag;
		
		return this;
	}
	
	public float magnitud()
	{
		return (float) Math.sqrt(x*x+y*y);
	}
	
	public Vector2F direction(float angle)
	{
		float mag = magnitud();
		
		x = (float) (Math.cos(angle)*mag);
		y = (float) (Math.sin(angle)*mag);
		
		return this;
	}
	
	public float angle()
	{
		return (float) Math.asin(y/magnitud());
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}
}