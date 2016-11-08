package com.cquinn.slimes;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;



public class ScoreScreen extends GameLoop 
{
	int counter = 90;
	
	public ScoreScreen ()
 	{
 		
 	}
	
	public boolean displayScreen(Image screen,Graphics g, GameContainer gc)
	{
		
		float scale =.4f;
		screen.draw(0,0,scale);
		counter--;
		if(counter>0)
		{
			return true; 
		}
		else
		{
			counter=90;
			return false;
		}
	}
	
	public void SetScore(String score,int pos,Graphics g, GameContainer gc)
	{
		g.drawString(score, pos, (groundh-300));
    	g.drawString(score, pos, (groundh-300));
	}

}
