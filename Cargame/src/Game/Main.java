package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cerebro on 01/07/16.
 */
public class Main implements KeyListener {

    public static Mycar mycar;
    public static int score=0;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        Dimension dimension1 = new Dimension(300, 700);
        panel.setPreferredSize(dimension1);
        panel.setFocusable(true);
        panel.addKeyListener(new Main());
        frame.pack();

        frame.setVisible(true);
        panel.requestFocus();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sleep function failed");
        }

        Graphics graphics2 = panel.getGraphics();
        Image tempImage = new BufferedImage(300, 700, BufferedImage.TYPE_INT_RGB);
        Graphics graphics1 = tempImage.getGraphics();



        Main.mycar = new Mycar(100);

        List<Traffic> cars = new ArrayList<>();
        cars.add(new Traffic(100));
        cars.add(new Traffic(200));
        cars.add(new Traffic(300));
        cars.add(new Traffic(350));
        cars.add(new Traffic(350));

        List<Background> backg = new ArrayList<>();
        backg.add(new Background(0));
        backg.add(new Background(-700));

        List<Element> elements = new ArrayList<>();
        elements.addAll(backg);
        elements.add(Main.mycar);
        elements.addAll(cars);



        while (true) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < cars.size(); i++) {
                Traffic b = cars.get(i);
                if (!b.hidden && Main.mycar.intersects(b)){
                    Main.mycar.hitAudio.play();
                    b.hidden = true;
                    score += 10;
                    break;
                }
            }
            graphics1.clearRect(0, 0, 300, 700);
            graphics1.drawString("Score: " + Integer.toString(score), 230, 20);

            for (int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i);
                e.update();
                if(!e.hidden) {
                    graphics1.drawImage(e.image, e.x, e.y, null);
                    Main.mycar.carrunAudio.loop();
                }
            }
            graphics2.drawImage(tempImage, 0, 0, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Main.mycar.moveright();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            Main.mycar.moveleft();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Main.mycar.brake();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Main.mycar.brakerelese();
        }
    }
}
