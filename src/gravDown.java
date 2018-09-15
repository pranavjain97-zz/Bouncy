import java.awt.Color;
import java.awt.Graphics;

public class gravDown extends item {

    private int newGravity;
    public gravDown(int x) {

        super(x);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void performAction(Ball b) {
        /* To make sure the gravity is never less than 3
         * and is always controlled by a factor of 3 everytime it exceeds 
         */
        System.out.print("GravDown");
        if (b.getGravity() > 3) {
            b.setGravity(b.getGravity() - 3);

            if (b.getGravity() < 3)
                b.setGravity(3);
        }
    }


    @Override
    public void paint(Graphics g) {

        g.setColor(Color.RED);
        super.paint(g);
    }
}