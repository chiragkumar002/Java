package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Chirag on 29-06-2016.
 */
public class Flyingbird implements KeyListener,MouseListener {

    private static Image bird1Image;
    private static Image bird2Image;
    private static Image bird3Image;
    private static Image bird4Image;
    private static Image bird5Image;
    private static Image bird6Image;
    private static Image bird7Image;
    private static Image bird8Image;
    private static Image plankImage;
    private static Image grassImage;
    private static Image standImage;
    private static Image angryImage;
    private static Image logImage;
    private static Image bgImage;
    private static Image berrieImage;
    private static Image overImage;
    private static Image pauseImage;
    private static AudioClip jumpAudio;
    private static AudioClip hitAudio;
    private static int birdXCord = 220;
    private static int birdYCord = 303;
    private static int birdYvel = 0;
    private static int birdYAccl = 0;
    private static int count = 0;
    private static Random randomGenerator = new Random();
    private static Rectangle birdRectangle = new Rectangle();
    private static Rectangle standRectangle = new Rectangle();
    private static Rectangle berrieRectangle = new Rectangle();
    private static Rectangle angryRectangle = new Rectangle();
    private static boolean standVisible = true;
    private static boolean angryVisible = true;
    private static boolean berrieVisible = true;
    private static boolean SetVisible = true;
    private static boolean gameOver = false;
    private static boolean background = true;
    private static boolean gamePaused = false;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        Dimension dimension1 = new Dimension(800, 450);
        panel.setPreferredSize(dimension1);
        panel.setFocusable(true);
        Flyingbird bird = new Flyingbird();
        panel.addKeyListener(bird);
        panel.addMouseListener(bird);
        frame.pack();

        frame.setVisible(true);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sleep function failed");
        }
        Graphics graphics2 = panel.getGraphics();
        Image tempImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_RGB);
        Graphics graphics1 = tempImage.getGraphics();

        //    Graphics graphics1 = panel.getGraphics();


        panel.requestFocus();

        try {
            Flyingbird.bgImage = ImageIO.read(Flyingbird.class.getResource("images/bg1.png"));
            Flyingbird.bird1Image = ImageIO.read(Flyingbird.class.getResource("images/pappu1.png"));
            Flyingbird.bird2Image = ImageIO.read(Flyingbird.class.getResource("images/pappu2.png"));
            Flyingbird.bird3Image = ImageIO.read(Flyingbird.class.getResource("images/pappu3.png"));
            Flyingbird.bird4Image = ImageIO.read(Flyingbird.class.getResource("images/pappu4.png"));
            Flyingbird.bird5Image = ImageIO.read(Flyingbird.class.getResource("images/pappu5.png"));
            Flyingbird.bird6Image = ImageIO.read(Flyingbird.class.getResource("images/pappu6.png"));
            Flyingbird.bird7Image = ImageIO.read(Flyingbird.class.getResource("images/pappu7.png"));
            Flyingbird.bird8Image = ImageIO.read(Flyingbird.class.getResource("images/pappu8.png"));
            Flyingbird.plankImage = ImageIO.read(Flyingbird.class.getResource("images/plank_bot.png"));
            Flyingbird.grassImage = ImageIO.read(Flyingbird.class.getResource("images/grass.png"));
            Flyingbird.logImage = ImageIO.read(Flyingbird.class.getResource("images/log1.png"));
            Flyingbird.standImage = ImageIO.read(Flyingbird.class.getResource("images/stand1.png"));
            Flyingbird.berrieImage = ImageIO.read(Flyingbird.class.getResource("images/berries.png"));
            Flyingbird.angryImage = ImageIO.read(Flyingbird.class.getResource("images/angry.png"));
            Flyingbird.overImage = ImageIO.read(Flyingbird.class.getResource("images/gameover.png"));
            Flyingbird.pauseImage = ImageIO.read(Flyingbird.class.getResource("images/pause.png"));
        } catch (IOException e) {

        }
        Image[] playerImages = {
                Flyingbird.bird1Image,
                Flyingbird.bird2Image,
                Flyingbird.bird3Image,
                Flyingbird.bird4Image,
                Flyingbird.bird5Image,
                Flyingbird.bird6Image,
                Flyingbird.bird7Image,
                Flyingbird.bird8Image,

        };

        int playerImageIndex = 0;
        Image currentPlayerImage;
        int standXCord = 900;
        int standYCord = 0;
        int berriesXCord = 1200;
        int berriesYCord = 100;
        int angryXCord = 1600;
        int angryYCord = 100;
        while (true) {
            Flyingbird.jumpAudio = Applet.newAudioClip(Flyingbird.class.getResource("images/onjump.wav"));
            Flyingbird.hitAudio = Applet.newAudioClip(Flyingbird.class.getResource("images/hit.wav"));


            if (Flyingbird.gamePaused) {
                continue;
            }

            if (gameOver) {
                break;
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            playerImageIndex++;
            playerImageIndex = playerImageIndex % 8;
            currentPlayerImage = playerImages[playerImageIndex];

            Flyingbird.birdYvel += Flyingbird.birdYAccl;
            Flyingbird.birdYCord += Flyingbird.birdYvel;

            if (Flyingbird.birdYCord >= 500) {
                Flyingbird.birdYvel = 0;
                Flyingbird.birdYAccl = 0;
            }
            standXCord -= 5;
            berriesXCord -= 5;
            angryXCord -= 5;

            if (standXCord < -20 && berriesXCord < -20 && angryXCord < -20) {
                standXCord = 900;
                berriesXCord = 1200;
                angryXCord = 1500;
                int randomInt = Flyingbird.randomGenerator.nextInt(2);

                if (randomInt == 0) {
                    standYCord = 0;
                    berriesYCord = 200;
                    angryYCord = 200;
                } else {
                    standYCord = 250;
                    berriesYCord = 250;
                    angryYCord = 300;
                }


                Flyingbird.standVisible = true;
                Flyingbird.berrieVisible = true;
                Flyingbird.angryVisible = true;
            }

            Flyingbird.standRectangle.setBounds(standXCord, standYCord, 59, 149);
            Flyingbird.birdRectangle.setBounds(Flyingbird.birdXCord, Flyingbird.birdYCord, 50, 50);

            Flyingbird.berrieRectangle.setBounds(berriesXCord, berriesYCord, 30, 42);
            Flyingbird.birdRectangle.setBounds(Flyingbird.birdXCord, Flyingbird.birdYCord, 50, 50);

            Flyingbird.angryRectangle.setBounds(angryXCord, angryYCord, 29, 29);
            Flyingbird.birdRectangle.setBounds(Flyingbird.birdXCord, Flyingbird.birdYCord, 50, 50);

            if (Flyingbird.birdRectangle.intersects(Flyingbird.standRectangle)) {

                Flyingbird.birdXCord -= 25;
                Flyingbird.birdYCord = 500;
                Flyingbird.gameOver = true;


            }
            if (Flyingbird.birdRectangle.intersects(Flyingbird.angryRectangle)) {
                Flyingbird.hitAudio.play();
                Flyingbird.birdXCord -= 25;
                Flyingbird.birdYCord = 500;
                Flyingbird.gameOver = true;


            }
            if (Flyingbird.berrieVisible && Flyingbird.birdRectangle.intersects(Flyingbird.berrieRectangle)) {
                Flyingbird.hitAudio.play();
                Flyingbird.berrieVisible = false;
                count++;
                System.out.println(count);
            }
            if (birdYCord > 500) {
                Flyingbird.gameOver = true;
            }
            if (birdYCord < -100) {
                Flyingbird.gameOver = true;
            }


            graphics1.clearRect(0, 0, 800, 450);
            if (background) {
                graphics1.drawImage(Flyingbird.bgImage, 0, 0, null);
            }
            graphics1.drawImage(Flyingbird.grassImage, 0, 405, null);
            graphics1.drawImage(currentPlayerImage, 220, Flyingbird.birdYCord, null);

            if (Flyingbird.standVisible) {
                graphics1.drawImage(Flyingbird.standImage, standXCord, standYCord, null);
            }
            if (Flyingbird.berrieVisible) {
                graphics1.drawImage(Flyingbird.berrieImage, berriesXCord, berriesYCord, null);
            }
            if (Flyingbird.angryVisible) {
                graphics1.drawImage(Flyingbird.angryImage, angryXCord, angryYCord, null);
            }


            if (SetVisible) {
                graphics1.drawImage(Flyingbird.logImage, 220, 354, null);
            }
            graphics1.setColor(Color.red);
            // graphics1.fillRect(740, 10, 50, 50);
            graphics1.drawImage(Flyingbird.pauseImage, 750, 12, null);
            graphics2.drawImage(tempImage, 0, 0, null);


        }

        graphics1.clearRect(0, 0, 800, 450);
        graphics1.drawImage(Flyingbird.overImage, 0, 0, null);
        graphics2.drawImage(tempImage, 0, 0, null);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            Flyingbird.birdYvel = -10;
            Flyingbird.birdYAccl = 1;
            SetVisible = false;
            Flyingbird.jumpAudio.play();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 740 && e.getX() < 790 && e.getY() > 10 && e.getY() < 60) {
            Flyingbird.gamePaused = !Flyingbird.gamePaused;
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



