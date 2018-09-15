import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    private int x = 400;
    private int y = 25;
    private double dx = 0; //Setting up dimensions of our ball
    private double dy = 0;
    private int rad = 20;
    private double gravity = 15;
    private double dt = .2;
    private double lossofenergy = 1; //0.7
    private double xfriction = 0.8;
    private double gameY = -75;
    /*For the keyboard events*/
    private double agility = 3;
    private double maxspeed = 20;
    private boolean game_over = false;


    public Ball() {
        // TODO Auto-generated constructor stub
    }

    public Ball(int i, int j) {

        x = i;
        y = j;
        // TODO Auto-generated constructor stub
    }

    public void updateBall(startingPoint o1) {
        //thread information

        if (x + dx > o1.getSize().width - rad) {
            x = o1.getSize().width - rad - 1;
            dx = -dx;
        } else
            x += dx;

        if (x + dx < 0 + rad) {
            x = 0 + rad;
            dx = -dx;
        } else {

            x += dx;
        }

        //		if(y+dy>o1.getSize().height-rad)
        //	    {   y=o1.getSize().height-rad-1;
        //		    dy=-dy; }
        //		else 
        //			y+=dy;
        //		
        //		if(y+dy<0+rad)
        //		{		y=0+rad;
        //		        dy=-dy;
        //		}	    	
        //		  

        //adding friction 
        if (y == o1.getHeight() - rad - 1)
            dx *= xfriction;

        if (Math.abs(dx) < .8) //speeding up the stopping process
            dx = 0;

        //adding gravity
        if (y - 100 > o1.getHeight() - rad - 1) {

            /** Old code for bouncing off using physics. Now just ends the game
            	y=o1.getHeight()-rad-1;
            	dy=lossofenergy*dy;
            	dy=gameY; 
            	*/
            game_over = true;

        } else {

            //velocity 
            dy += gravity * dt;

            //position
            y += dy * dt + .5 * gravity * dt * dt;

        }
    }

    public void moveToRight() {
        if (dx + 1 < maxspeed) //determining the speed of movement. allowing it go right till speed of 20
            //			dx+=1;
            dx += agility;
    }

    public void moveToLeft() {
        if (dx - 1 > -maxspeed)
            //			dx-=1;
            dx -= agility;
    }

    /****x=0, y=0; so initially it will be in the top left corner of the screen. Thats where java starts working.  
       build in functions of java 
       1st radius= defines the distance away it is in width from the x Here: 10 //2nd one down with y  ***/


    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x - rad, y - rad, rad * 2, rad * 2);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public int getRad() {
        return rad;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setRad(int rad) {
        this.rad = rad;
    }

    public double getGameY() {
        return gameY;
    }

    public void setGameY(double gameY) {
        this.gameY = gameY;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(double maxspeed) {
        this.maxspeed = maxspeed;
    }


    public boolean isGame_over() {
        return game_over;
    }

    public void setGame_over(boolean game_over) {
        this.game_over = game_over;
    }
}