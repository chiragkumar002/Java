package in.codekamp.WrestlerGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by cerebro on 10/04/16.
 */
public class WrestlerCard extends JPanel {
    public JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel imagePanel;
    private JLabel nameLabel;
    private JLabel rankLabel;
    private JLabel matchesWonLabel;
    private JLabel matchesLostLabel;

    private boolean isOpen = false;
    private Wrestler wrestler = null;
    private Image backCoverImage;

    public void setWrestler(Wrestler wrestler) {
        this.wrestler = wrestler;
        if (this.isOpen) {
            this.reload();
        }
    }

    public void open() {
        this.isOpen = true;
        this.reload();
    }

    public void close() {
        this.isOpen = false;
        this.reload();
    }

    private void reload() {
        Graphics g = imagePanel.getGraphics();
        if (this.isOpen && this.wrestler != null) {
            g.drawImage(this.wrestler.getImage(), 0, 0, null);
            this.infoPanel.setVisible(true);
            this.nameLabel.setText(this.wrestler.getName());
            this.rankLabel.setText(Integer.toString(this.wrestler.getRank()));
            this.matchesWonLabel.setText(Integer.toString(this.wrestler.getMatchesWon()));
            this.matchesLostLabel.setText(Integer.toString(this.wrestler.getMatchesLost()));
        } else {
            this.infoPanel.setVisible(false);
            g.drawImage(this.getBackCoverImage(), 0, 0, null);
        }
    }


    private Image getBackCoverImage() {
        if (this.backCoverImage == null) {
            try {
                this.backCoverImage = ImageIO.read(WrestlerCard.class.getResource("images/background.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this.backCoverImage;
    }

}
