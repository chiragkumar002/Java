package Game;


import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by cerebro on 01/07/16.
 */
public class Mycar extends Element {

     public AudioClip hitAudio;
     public AudioClip carrunAudio;

     public Mycar(int y) {
        super(120, 120);
         this.x = 70 ;
        this.y = 550;
        this.yVel=0;
        this.carrunAudio = Applet.newAudioClip(Mycar.class.getResource("resources/car.wav"));
        this.hitAudio = Applet.newAudioClip(Mycar.class.getResource("resources/crash.wav"));

    }

@Override
    protected String getImagePath() {
        return "resources/car.png";
    }
    public void moveright() {
            this.x = 150;
    }

    public void moveleft() {
        this.x = 70;
    }
    public void brake() {
        this.yVel = 0;
    }
    public void brakerelese() {
        this.yVel =-4;
    }
}

