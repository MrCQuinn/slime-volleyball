package com.cquinn.slimes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;


public class Slime extends GameLoop
{
 	Image SlimeImage;
 	float xcoord;
 	float ycoord;
 	float originalx;
 	float originaly;
 	float deltax = 0;
 	float deltay = 0;
 	float jumpPower = 0f;
 	float gravitationalk;
 	float slimewidth;
 	float slimeheight;
 	Ellipse Circly;
 	
 	public Slime(Image picture, float startx, float starty, float scaleinslime)
 	{
 		//put in images and starting positions.
		
		xcoord= startx;
		ycoord = starty;
		originalx = startx;
		originaly = starty;
		SlimeImage = picture;
		slimewidth = scaleinslime*493;
		slimeheight = scaleinslime*400;
		Circly = new Ellipse(xcoord,ycoord, (slimewidth/2),(slimeheight/2));
		gravitationalk = .2f;
 	}
	
	public void mydraw(float scaleinslime, Graphics g, GameContainer gc)
	{
		SlimeImage.draw(xcoord,ycoord,scaleinslime);
		//g.draw(Circly);
		slimewidth = scaleinslime*500;
		slimeheight = scaleinslime*350;
	}
	
	public void Movement()
	{	
		// if slime is above ground
		if(ycoord < (groundh-slimeheight))
		{
			jumpPower = jumpPower*.79f;
			deltay = gravitationalk + deltay;
		}
		
		// if slime is below ground
		if(ycoord > (groundh-slimeheight))
		{
			ycoord = groundh-slimeheight;
		}
		
		
		if(ycoord == (groundh-slimeheight))
		{
			jumpPower = 8.7f;
		}
		
		ycoord = ycoord + deltay;
		xcoord = xcoord + deltax;
		
		Circly.setCenterX(xcoord+(slimewidth/2));
		Circly.setCenterY(ycoord+(slimeheight));
	}
	
	public void stopX(){
		deltax = 0;
	}
	
	public void moveLeft(float leftBoundary, GameContainer gc, int delta)
			throws SlickException
	{
		if(xcoord>=leftBoundary)
    	{	
			deltax= -4.5f;
    	}
		
	}
	
	public void moveRight(double rightBoundary, GameContainer gc, int delta)
			throws SlickException
	{
		if(xcoord<=rightBoundary)
    	{	
			deltax= 4.5f;
    	}
		
	}
	void jump()
	{
		deltay = -jumpPower;
	}

	void serve()
	{
		xcoord = originalx;
		ycoord = originaly;
	}

}
