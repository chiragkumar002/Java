package Game;

import java.util.Random;

/**
 * Created by Chirag on 01-07-2016.
 */
public class Balloon extends Element  {
    private static Random randomGenerator = new Random();
    private static Random randomGenerator1 = new Random();


    public Balloon(int y) {
        super(40, 50);
        this.y = y;
        this.yVel = -10;
        this.setRandomX();
    }
    public void handleClick(int clickX, int clickY) {
        if (this.isClicked(clickX, clickY)) {
         //   resources.burstAudio.play();
            this.hidden = true;
        }
    }

    protected String getImagePath() {
        int randomInt1 = randomGenerator1.nextInt(5);
        if( randomInt1 == 0){
            return "resources/balloon_olive_converted.png";
        }
        if( randomInt1 == 1){
            return "resources/balloon_orange_converted.png";
        }
        if( randomInt1 == 2){
            return "resources/balloon_pink_converted.png";
        }
        if( randomInt1 == 3){
       return "resources/balloon_blue_converted.png";
        }
        if( randomInt1 == 4) {
       return "resources/balloon_purple_converted.png";
        }
            else {
        return "resources/balloon_brown_converted.png";}}

    protected void update() {
        super.update();

        if (this.y < -100) {
            this.y = 700;
            this.setRandomX();
        }

    }

    protected void setRandomX() {
        int randomInt = randomGenerator.nextInt(6);

        if (randomInt == 0) {
            this.x = 60;
        } else if (randomInt == 1) {
            this.x = 120;
        } else if (randomInt == 2) {
            this.x = 180;
        } else if (randomInt == 3) {
            this.x = 300;
        } else if (randomInt == 4) {
            this.x = 400;
        } else if (randomInt == 5) {
            this.x = 525;
        }

    }

}
