
import java.awt.Color;
import java.awt.Graphics;

public class AgilDown extends item {
	
	private int newGravity; 

	public AgilDown(int x) {
		
		super(x);
		// TODO Auto-generated constructor stub
	}
	
	
    @Override
	public void performAction(Ball b) {
		// TODO Auto-generated method stub
		if(b.getAgility()<8){
			b.setAgility(b.getAgility()-1);
		}
		
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {

		g.setColor(Color.YELLOW); 
		super.paint(g);
	}
	
	
	
	
}
