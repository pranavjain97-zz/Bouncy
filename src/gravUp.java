import java.awt.Color;
import java.awt.Graphics;

public class gravUp extends item {

    private int newGravity;

    public gravUp(int x) {

        super(x);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void performAction(Ball b) {
        // TODO Auto-generated method stub
        b.setGravity(b.getGravity() + 1.2);
        System.out.print("GravUp");
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.RED);
        super.paint(g);
    }
}