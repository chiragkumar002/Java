package Game;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;


/**
 * Created by Chirag on 01-07-2016.
 */
public class Opps implements MouseListener{
public static boolean

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        Dimension dimension1 = new Dimension(625, 625);
        panel.setPreferredSize(dimension1);
        panel.setFocusable(true);
        panel.addMouseListener( new Opps());
        frame.pack();
        frame.setVisible(true);


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sleep function failed");
        }
        Graphics graphics2 = panel.getGraphics();
        Image tempImage = new BufferedImage(625, 625, BufferedImage.TYPE_INT_RGB);
        Graphics graphics1 = tempImage.getGraphics();
    //    Graphics graphics1 = panel.getGraphics();
        List<Balloon> balloons = new ArrayList<>();
        balloons.add(new Balloon(700));
        balloons.add(new Balloon(725));
        balloons.add(new Balloon(750));
        balloons.add(new Balloon(775));
        balloons.add(new Balloon(800));
        balloons.add(new Balloon(825));

        List<Element> elements = new ArrayList<>();
        elements.addAll(balloons);

        Image bgImage = null;
        try {
            bgImage = ImageIO.read(Opps.class.getResource("resources/bgballon1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            graphics1.clearRect(0, 0, 625, 625);
            graphics1.fillRect(0, 0, 625, 625);
            graphics1.drawImage(bgImage, 0, 0, null);

            for (int i = 0; i <balloons.size(); i++) {
                Element element = elements.get(i);
                element.update();
                if (!element.hidden) {
                    graphics1.drawImage(element.image, element.x, element.y, null);
                }
            }
            graphics2.drawImage(tempImage, 0, 0, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 20 && e.getX() < 580 && e.getY() > 200 && e.getY() < 300){


        }

    }


    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
