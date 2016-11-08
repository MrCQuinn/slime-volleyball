package com.cquinn.slimes;

 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;




public class GameLoop extends BasicGame {
	
	float groundh = 500;
	
	Slime PlayerTwo;
	Slime PlayerOne;
	Ball ball;
	ScoreScreen Pone;
	ScoreScreen Ptwo;
	
	Image slimepicture1 = null;
	Image slimepicture2 = null;
	Image background = null;
	Image ballpic = null;
	Image ScoreImage = null;
	
	float scalep1 = .2f;
	float scalep2 = .2f;
	
	float x = 100;
	float y = (groundh-(350*scalep1));
	
	float x1 = 550;
	float y1 =(groundh-(350*scalep2));
	
	float ballx = 175;
	float bally = 100;
	
	float scaleball = .1f;
	
	int PlayerOneScore = 5;
	int PlayerTwoScore = 5;
	
	boolean Player1Scored = false;
	boolean Player2Scored = false;
	
	long timeOfNextUpdate;
    long timeLeft;
	
	
	
	
	
	public GameLoop()
    {
        super("SlimeVolleyball - SimpleGame");
        
    }
 
	
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	
    	
    	
    	slimepicture1 = new Image("res/Slime1.png");
    	//500x350
    	//after scale, 150x105
    	//actual height = 53?
    	slimepicture2 = new Image("res/Slime2.png");
    	background = new Image("res/background.bmp");
    	//800x600, ground 800x100
    	ballpic = new Image("res/ball.png");
    	//200x200
    	//20x20 after scale
    	ScoreImage = new Image("res/score.bmp");
    	
    	PlayerOne = new Slime(slimepicture1,x,y,scalep1);
    	PlayerTwo= new Slime(slimepicture2,x1,y1,scalep2);
    	ball = new Ball(ballpic, ballx, bally);
    	Pone = new ScoreScreen();
    	Ptwo = new ScoreScreen();
    	
    	
 
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	timeOfNextUpdate = (System.nanoTime() / 1000000) + 17;
    	
    	Input input = gc.getInput();
    	 
    	if(ball.ballshape.intersects(PlayerOne.Circly))
    	{
    		ball.SlimeDetection(PlayerOne.Circly,PlayerOne.xcoord,PlayerOne.ycoord, PlayerOne.deltax, PlayerOne.deltay, delta, PlayerOne.slimewidth);
    	}
    	
    	if(ball.ballshape.intersects(PlayerTwo.Circly))
    	{
    		ball.SlimeDetection(PlayerTwo.Circly, PlayerTwo.xcoord,PlayerTwo.ycoord, PlayerTwo.deltax, PlayerTwo.deltay, delta, PlayerOne.slimewidth);
    	}
    	
    	ball.WallDetection();
    	ball.NetDetection();
    	
    	int WhoScored = ball.Score();
    	
    	if(WhoScored==1)
    	{
    		PlayerOneScore++;
    		PlayerTwoScore--;
    		Player1Scored = true;
    	}
    	
    	if(WhoScored==2)
    	{
    		PlayerOneScore--;
    		PlayerTwoScore++;
    		Player2Scored = true;
    	}
    	
    	PlayerOne.stopX();
    	PlayerTwo.stopX();
    	
        if(input.isKeyDown(Input.KEY_A))
        {	
        	PlayerOne.moveLeft(0, gc, delta);
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
            PlayerOne.moveRight((400-PlayerOne.slimewidth),gc, delta);	
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
        	PlayerOne.jump();
        }
        
        if(input.isKeyDown(Input.KEY_LEFT))
        {
        		PlayerTwo.moveLeft(405, gc, delta);
        }
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
        	PlayerTwo.moveRight((800-PlayerTwo.slimewidth), gc, delta);	
        }
 
        if(input.isKeyDown(Input.KEY_UP))
        {
            PlayerTwo.jump();
        }
        
        PlayerOne.Movement();
    	PlayerTwo.Movement();
    	ball.Movement();
    	ball.Gravity();
        
        //cap frame rate (2016 update)
        timeLeft = timeOfNextUpdate - (System.nanoTime() / 1000000);
        if(timeLeft > 0){
        	try {
				Thread.sleep(timeLeft);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	String GameTitle = "Ultimate Slime Voll3yball";
    	String OneSco = "" + PlayerOneScore;
    	String TwoSco = "" + PlayerTwoScore;
    	
    	background.draw(0, 0);
    	
    	PlayerOne.mydraw(scalep1,g,gc);
    	
    	PlayerTwo.mydraw(scalep2,g,gc);
    	
    	Pone.SetScore(OneSco,200,g,gc);
    	Ptwo.SetScore(TwoSco,600,g,gc);
    	
    	g.drawString(GameTitle,300,300);
    	
    	ball.mydraw(scaleball,g, gc);
    	
    	if(Player1Scored == true)
    	{
    		Player1Scored = Pone.displayScreen(ScoreImage, g,gc);
    		PlayerOne.serve();
    		PlayerTwo.serve();
    		ball.ServeBall(PlayerOne.xcoord,PlayerOne.slimewidth);
    	}
    	if(Player2Scored == true)
    	{
    		Player2Scored = Ptwo.displayScreen(ScoreImage, g,gc);
    		PlayerOne.serve();
    		PlayerTwo.serve();
    		ball.ServeBall(PlayerTwo.xcoord,PlayerTwo.slimewidth);
    		
    	}
    	
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new GameLoop());
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}


