package com.cquinn.slimes;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;


public class Ball extends GameLoop
{
	
Image BallImage;
float xcoord;
float ycoord;
float dx = 0f;
float dy = .04f;
float gravitationalkball;
float ballheight;
float ballwidth;
Circle ballshape;
Rectangle net;
int counter = 90;

public Ball(Image picture, float startx, float starty)
	{
		//put in images and starting positions.
				xcoord= startx;
				ycoord = starty;
				BallImage = picture;
				ballshape = new Circle((startx+10),(starty+10),10);
				net = new Rectangle(398,447,6,60);
				ballheight = 200*scaleball;
				ballwidth = 200*scaleball;	
				gravitationalkball = .2f;
	}

	public void mydraw(float scale, Graphics g, GameContainer gc)
	{	
		//add a check to see who scored last
		BallImage.draw(xcoord,ycoord,scale);
		//g.draw(ballshape);
		//g.draw(net);
	}
	
	public void Movement()
	{
		xcoord = xcoord + dx;
		ycoord = ycoord + dy;
		dy = dy *.99f;
		
		ballshape.setCenterX(xcoord + 10);
		ballshape.setCenterY(ycoord + 10);
	}
	
	public void Gravity()
	{
		if(ycoord>=480)
		{
			ycoord=480;
			//Slime.Score()
		}
		else
		{
			dy = dy + gravitationalkball;
		}
		
		ballshape.setCenterX(xcoord +10);
		ballshape.setCenterY(ycoord+10);
	}
	public void NetDetection()
	{
		if(ballshape.intersects(net))
		{
			//ball.bounce(delta);
			dx = -dx;
			dy = -.7f;
		}
	}

	public void SlimeDetection(Ellipse Circly, float slimex, float slimey, float Slimedx, float Slimedy, float delta,float slimewidth)
	{
		if((ycoord<=(slimey+105)))
		{
			dy = Slimedy - 2.7f;
			dx = Slimedx + 1.3f;
			
			if((xcoord+(ballwidth))<(slimex+(slimewidth)/2))
			{
				dx = -dx;
			}
		}
	}
	public void WallDetection()
	{
		if(xcoord<=0)
		{
			dx = -dx;
		}
		if(xcoord>= (800-ballwidth))
		{
			dx=-dx;
		}
		if(xcoord>(800-ballwidth))
		{
			xcoord = (800-ballwidth);
		}
		if(xcoord<0)
		{
			xcoord = 0;
		}
	}
	
	public int Score()
	{
		int whoScored=3;
		
		if(ycoord>=(groundh-ballheight))
		{
			if(xcoord<397)
			{
				whoScored=2;
			}
			if(xcoord>403)
			{
				whoScored=1;
			}
		}
		
		return whoScored;
	}
	public void ServeBall(float slimex,float widthofslime)
	{
			xcoord = slimex + (widthofslime/2) - (ballwidth/2);
			ycoord = 300;
			if (counter > 0)
			{
				dx = 0f;
				dy = 0f;
				counter--;
			}
			else 
			{
				dx = .0f;
				dy = .03f;
				counter =700;
			}
		
	}


}
