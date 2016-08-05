package Game;

/**
 * Created by cerebro on 01/07/16.
 */
public class Background extends Element {

    public Background(int y) {
        super(300, 700);
        this.y = y;
        this.yVel = 3;
    }

    @Override
    protected String getImagePath() {
        return "resources/cartrack.png";
    }

    @Override
    protected void update() {
        super.update();

        if (this.y >= 700) {
            this.y = -700;

        }
    }}



