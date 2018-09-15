import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class item {

    private int x, y, dx, rad;
    private int count;
    private startingPoint sp;
    private boolean createNew = false;

    public item(int x) {

        this.x = x;
        rad = 10;
        Random r = new Random();
        this.y = r.nextInt(450) + rad;

        dx = -2;
    }

    public void updateItem(startingPoint sp, Ball b) {
        x += dx; //keep going left
        this.sp = sp;
        if (x < 0 - rad) {
            //	    	Random r=new Random();
            //	    	x=sp.getWidth()+r.nextInt(300)+2000;  //replacing with CreateNew

            createNew = true;
        }
        collisionDetection(b);

    }

    public void paint(Graphics g) {
        //		 g.setColor(Color.GREEN); 
        g.fillOval(x - rad, y - rad, rad * 2, rad * 2);

    }

    private void collisionDetection(Ball b) {

        double ballx = b.getX();
        double bally = b.getY();
        double ballRad = b.getRad();

        /*******collision detection of 2 balls*********/

        double a1 = ballx - x; //PYTHO 2 lengths 
        double b1 = bally - y;
        double collision = ballRad + rad; //distance b/w centres of 2 balls- fixed 
        double c = Math.sqrt((a1 * a1) + (b1 * b1)); //distance b/w 2 centres dynamic 


        if (c < collision) {

            performAction(b);
            //			 x=0; 
            //			 y=sp.getHeight()+100;    older version //100 below the screen.  //have explicitly defined the sp constructor 
            createNew = true;
        }
    }

    /* Getters and Setters*/

    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public int getDx() {
        return dx;
    }


    public void setDx(int dx) {
        this.dx = dx;
    }


    public int getRad() {
        return rad;
    }


    public void setRad(int rad) {
        this.rad = rad;
    }

    public boolean isCreateNew() {
        return createNew;
    }

    public void setCreateNew(boolean createNew) {
        this.createNew = createNew;
    }

    public void performAction(Ball b) {
        // TODO Auto-generated method stub

    }
}