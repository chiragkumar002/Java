package Game;

import java.util.Random;

/**
 * Created by cerebro on 01/07/16.
 */
public class Traffic extends Element {

    public static Random randomGenerator = new Random();
    public static Random randomGenerator1 = new Random();
    public Traffic(int y) {
        super(23, 47);
        this.y = y;
        this.x = 70;
        this.yVel = 5;
        this.setRandomX();
    }

@Override
    protected String getImagePath() {
    int randomInt = Traffic.randomGenerator1.nextInt(2);
    if (randomInt == 0) {
        return "resources/car3.png";
    } else  {
        return "resources/Tank2.png";
    }
}




    @Override
    protected void update() {
        super.update();

        if (this.y > 800) {
            this.y = -50;
            this.setRandomX();
            this.hidden = false;
        }
    }

    protected void setRandomX() {
        int randomInt = Traffic.randomGenerator.nextInt(3);

        if (randomInt == 0) {
            this.x = 160;
        } else if (randomInt == 1) {
            this.x = 200;
        } else
            this.y = 240;
        }
    }


