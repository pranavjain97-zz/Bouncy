
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Platform {
	
	private int x=300;
	private int y=300;
	private int width=130;
	private int height=40;
	private int dx=0; 
	

	public Platform() {
		dx=-1;
	// TODO Auto-generated constructor stub
    }

    public Platform(int i,int j) {
	   this.x=i;
	   this.y=j;
	   width=130;
	   height=40;
	   dx=-1; 
	 
	// TODO Auto-generated constructor stub
}

	public void updatePlat(startingPoint o1,Ball b){
		
		x-=musicOn.level;
		
//		x+=dx;  //keep going left
	    if(x<0-width){  //recurring platforms 
		
		
		Random r=new Random(); //random heights and widths
		y=o1.getHeight()-40-r.nextInt(400);
		x=o1.getWidth()+r.nextInt(300);
		
	    }
		collisionDetection(b);
		
			}	
	
	public void paint(Graphics g){ 

		 g.setColor(Color.BLUE); 
		 g.fillRect(x,y,width,height); 
		
	}
	
	private void collisionDetection(Ball b) {
	
		
		double ballx=b.getX();
		double bally=b.getY();
		double rad=b.getRad();
	if(bally+rad>y && bally+rad<(y+height) && ballx+rad>x && ballx<x+width) {	
		
		double newY=b.getGameY();
	     b.setDy(newY);
	     musicOn.bounce.play();
	
//		double newX=b.getDx()*-1;
//		b.setDx(newX);
	}		
	
  }
}
