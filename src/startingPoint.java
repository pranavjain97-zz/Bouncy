

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random; 


/* Using Threads- allows us to use multiple things on the computer
 * Can create applications/games with 'Applet
 *  //Hold Ctrl+Space for the inbuilt functions @Override
 */

	
public class startingPoint extends Applet implements Runnable, KeyListener , MouseMotionListener, MouseListener{ 
	                                                           


	Graphics g; 
	private Image i; 
	public Graphics doubleG;
	Ball b;
//Ball b2;
	Platform p[]=new Platform[6]; 
	item item[]=new item[5];
	private int score; 
	private double cityX=0; 
	double cityDx=.4; 
	URL url; 
	Image city;
	private double levelcheck=0;
	private boolean gameOver=false; 
	private boolean mouseIn=false;
 
	
	
	@Override
	public void init() {  //gets called only the first time
   
	setSize(1000,666);  //variables width, height assigned 
	addKeyListener(this);
	addMouseMotionListener(this);
	addMouseListener(this);
	/* Gets the background image at start*/
	try{
		
		url=getDocumentBase(); 
	}catch (Exception e) {
			// TODO: handle exception
		}
	
	city=getImage(url,"images/back7.jpg");
	
	new musicOn(this);
	musicOn.music.play();
	
	
	
}

/****will set up the game, will be get called almost everytime ****/
	@Override
	public void start() {  
	
	score=0;
	

	b=new Ball();
//	b2=new Ball(200,30); 
	
	for( int i=0;i<p.length;i++)
	{	Random h=new Random();
	/*starting position of the platform where the ball lands*/
	
	p[i]=new Platform(i*130,300); } 
	
	
	for( int i=0;i<item.length;i++)
	{	Random r=new Random();
	item[i]=new gravUp(getWidth()+2000*i); 
	switch(r.nextInt(5)) 
	  {
	  case 0:item[i]=new gravUp(getWidth()+2000*i);
	  		 break;
	  case 1:item[i]=new gravDown(getWidth()+2000*i);
    		 break;
	  case 2:item[i]=new AgilUp(getWidth()+2000*i);
             break;
	  case 3:item[i]=new AgilDown(getWidth()+2000*i);
             break; 
	  case 4:item[i]=new Score(getWidth()+2000*i,this);
		     break; 
	  }
	}
	
//	p2=new Platform(100,500);
	Thread t=new Thread(this);  //automatically calls the Run() method from Runnable 
		t.start(); 
		
	}

	@Override
	public void run() {
	
	while(true)
	{
		int xTemp;
		for(int i=0;i<p.length;i++){
			
			int fakei=i; 
			xTemp=p[i].getX();
			if(xTemp<0-p[i].getWidth()){
			Random r=new Random();
			if(i==0){
				fakei=i+p.length;
				
			}
			
			p[i].setX(p[fakei-1].getX()+p[i].getWidth()+musicOn.level*10*r.nextInt(25));	
			}
			
		}
		
		
		gameOver=b.isGame_over(); 
		
		
		if(levelcheck>1000)
		{
		  musicOn.level++;
		  levelcheck=0;
		}
		if(!gameOver){
			
			levelcheck++;
		}
		
		
				
		if(cityX>getWidth()*-1)
		cityX-=cityDx;
		else{
			cityX=0;
		}
		
		if(!gameOver)
		{
			score+=1;
		}
		
		Random r=new Random();
		/***** for 2 balls collision, this is what happens to item. 
		    Passing the x position as the width of the program
		    Check if item is 100 px below the window  *****/
		
		for(int i=0;i<item.length;i++)
		{
			
			
			if(item[i].isCreateNew()){
			  item[i]=null;
			  switch(r.nextInt(2)) 
			  {
			  case 0:item[i]=new gravUp(getWidth()+r.nextInt(500)*10);
			  		 break;
			  case 4:item[i]=new gravDown(getWidth()+r.nextInt(500)*10);
	          		 break;
			  case 2:item[i]=new AgilUp(getWidth()+r.nextInt(500)*10);
	                 break;
			  case 3:item[i]=new AgilDown(getWidth()+r.nextInt(500)*10);
	                 break; 
			  case 1:item[i]=new Score(getWidth()+r.nextInt(500)*10,this);
				     break; 
			      
			  }
			  /* Making it false again if its on left of our screen or after a collision*/
			  item[i].setCreateNew(false); 
		   }
		}       
	
		
		
		b.updateBall(this);
//		b2.updateBall(this);
		for( int i=0;i<p.length;i++)
		p[i].updatePlat(this,b);
//		p2.updatePlat(this,b);
		
		for( int j=0;j<item.length;j++)
			item[j].updateItem(this,b);
		
		
	/*calls paint method ( built in) */	
		repaint(); 
		
	
	/* Making it 60 frames/s. ( 1000/60=16.99) 
       It will repaint it approx 17 times now*/	
	try{
		Thread.sleep(17);   
	
	}catch(InterruptedException e) {
	 e.printStackTrace(); }
	  
 }	
}	

/* uses for solving the issue of double buffer. Can just copy paste this function always the same
 * used to fix bugs in all java game apps */	
	
	@Override
	public void update(Graphics g) {   
		    
	if(i==null)  //if there is no image initially 
	{
		i=createImage(this.getSize().width,this.getSize().height);
		doubleG=i.getGraphics();
			}
	
	doubleG.setColor(getBackground());  //sets background of app 
	doubleG.fillRect(0,0,this.getSize().width,this.getSize().height); //same thing as above
	doubleG.setColor(getForeground());
	paint(doubleG);
	
	g.drawImage(i,0, 0, this);  //draws the image finally!
		
	}	

   
	@Override 
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {  //if we wanna save data for the game
		
		
	}
	
	@Override
	public void paint(Graphics g) {   //all the graphics
//		

     g.drawImage(city, (int)cityX, 0, this);
     g.drawImage(city, (int)cityX+getWidth(), 0, this);
//     b2.paint(g);
     for( int i=0;i<p.length;i++)
     p[i].paint(g);
//     p2.paint(g);
	
     for( int i=0;i<item.length;i++)
 		item[i].paint(g);
	   
        b.paint(g);
        
        
        
        
        Font fontLevel=new Font("Serif",Font.BOLD,22);
        g.setFont(fontLevel);
        g.setColor(Color.WHITE);
        g.drawString("Level: "+musicOn.getLevel(), getWidth()-100, 80);
        
        
    /* Converting our score variable to a String */
        
        
      String s=Integer.toString(score); 
      Font font=new Font("Serif",Font.BOLD,33);
      g.setFont(font);
      g.setColor(Color.BLACK);
      g.drawString(s, getWidth()-100, 50);
      g.setColor(Color.YELLOW);
      g.drawString(s, getWidth()-100+2, 50+2);
      
      
      if(gameOver){
    	 
    	 g.setColor(Color.WHITE);
    	 g.drawString("GAME OVER", 400, 360);
   
    	 if(mouseIn){
    		 g.setColor(Color.RED);
    		 g.drawString("Play Again", 417, 410);
    	 }else{
    		 g.setColor(Color.WHITE);
    		 g.drawString("Play Again",417,410);
    	 }
    	 
    	 
    	  
    	  
      }
}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	switch(e.getKeyCode()) {
	
	case KeyEvent.VK_LEFT: b.moveToLeft(); 
	                       break;
	case KeyEvent.VK_RIGHT:b.moveToRight();
	                       break; 
	} 
	
}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getScore() {
	return score;
	}

	public void setScore(int score) {
	this.score = score;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX()>417 && e.getX()<575){
			if(e.getY()>390 && e.getY()<410){
				mouseIn=true; 
			}
		}
		
		if(e.getX()<417 || e.getX()>575){
				mouseIn=false; 
			}
		
		if(e.getY()<390 || e.getY()>410){
			 mouseIn=false;
		}
			
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(mouseIn){
			
			b=new Ball(); 
			
			musicOn.level=1; 
			musicOn.music.play();
			score=0;
			levelcheck=0; 
			
			
			
			for( int i=0;i<p.length;i++)
			{	Random h=new Random();
			/*starting position of the platform where the ball lands*/
			
			p[i]=new Platform(i*130,300); } 
			
			
			for( int i=0;i<item.length;i++)
			{	Random r=new Random();
			item[i]=new gravUp(getWidth()+2000*i); 
			switch(r.nextInt(5)) 
			  {
			  case 0:item[i]=new gravUp(getWidth()+2000*i);
			  		 break;
			  case 1:item[i]=new gravDown(getWidth()+2000*i);
		    		 break;
			  case 2:item[i]=new AgilUp(getWidth()+2000*i);
		             break;
			  case 3:item[i]=new AgilDown(getWidth()+2000*i);
		             break; 
			  case 4:item[i]=new Score(getWidth()+2000*i,this);
				     break; 
			  }
			}
			
			
			
			
			
			
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
	
	
	
	
	
	
	
	
	

