

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Score extends item{
	

	private startingPoint appInfo;
	
	public Score(int x,startingPoint appInfo) {
		// TODO Auto-generated constructor stub
		super(x);
		this.appInfo=appInfo; 
	}
	
	
	
	public void performAction(Ball b) {
		
	
		Random r=new Random(); 
		appInfo.setScore(appInfo.getScore()+500+r.nextInt(2000));

		
	}
	
	
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.blue);
		super.paint(g);
	}
}
 
  
