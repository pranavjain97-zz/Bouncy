import java.awt.Color;
import java.awt.Graphics;

public class AgilUp extends item {

    private int newGravity;

    public AgilUp(int x) {

        super(x);
        // TODO Auto-generated constructor stub
    }

    public void performAction(Ball b) {
        // TODO Auto-generated method stub
        if (b.getAgility() >= 2) {
            b.setAgility(b.getAgility() - 1);
        }

    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.ORANGE);
        super.paint(g);
    }
}