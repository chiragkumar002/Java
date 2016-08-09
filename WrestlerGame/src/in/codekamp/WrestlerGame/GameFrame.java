package in.codekamp.WrestlerGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cerebro on 10/04/16.
 */
public class GameFrame extends JFrame implements ActionListener {
    private JButton playButton;
    private JButton resetButton;
    private JPanel mainPanel;
    private JPanel player1CardHolder;
    private JPanel player2CardHolder;
    private JComboBox selectionBox;
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;


    private static final String WINDOW_TITLE = "CodeKamp Wrestler Game";
    private WrestlerCard player1Card;
    private WrestlerCard player2Card;
    private List<Wrestler> player1Wrestlers;
    private List<Wrestler> player2Wrestlers;
    private int turn = 0;

    public GameFrame() {
        super(GameFrame.WINDOW_TITLE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.player1Card = new WrestlerCard();
        this.player2Card = new WrestlerCard();

        this.player1CardHolder.add(this.player1Card.mainPanel);
        this.player2CardHolder.add(this.player2Card.mainPanel);

        this.playButton.addActionListener(this);
        this.resetButton.addActionListener(this);

        this.setContentPane(this.mainPanel);
        this.pack();
    }

    public void start() {
//        this.player1Wrestlers = new LinkedList<Wrestler>();
//        this.player2Wrestlers = new LinkedList<Wrestler>();
//
//        List<Wrestler> allWrestlers = Wrestler.getAllWrestlers();
//        Collections.shuffle(allWrestlers);
//
//        int counter = 0;
//        while (!allWrestlers.isEmpty()) {
//            counter++;
//
//            if((counter % 2) == 0) {
//                this.player2Wrestlers.add(allWrestlers.remove(0));
//            } else {
//                this.player1Wrestlers.add(allWrestlers.remove(0));
//            }
//        }

        this.player1Wrestlers = Wrestler.getAllWrestlers();
        this.player2Wrestlers = Wrestler.getAllWrestlers();

        this.reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.playButton) {
            this.player1Card.open();
            this.player2Card.open();

            int selectedIndex = this.selectionBox.getSelectedIndex();

            int result = this.player1Wrestlers.get(0).compare(this.player2Wrestlers.get(0), selectedIndex);

            if(result > 0) {
                this.player1Wrestlers.add(this.player2Wrestlers.remove(0));
                JOptionPane.showMessageDialog(this, "player 1 won!");
            } else if (result < 0) {
                this.player2Wrestlers.add(this.player1Wrestlers.remove(0));
                JOptionPane.showMessageDialog(this, "player 2 won!");
            } else {
                JOptionPane.showMessageDialog(this, "match draw!");
            }
        } else if(e.getSource() == this.resetButton) {
            this.reset();
        }
    }

    private void reset() {
        this.turn++;

        int player1CardCount = this.player1Wrestlers.size();
        int player2CardCount = this.player2Wrestlers.size();

        if(player1CardCount == 0) {
            JOptionPane.showMessageDialog(this, "player 1 game over!");
            return;
        } else if(player1CardCount == 0) {
            JOptionPane.showMessageDialog(this, "player 2 game over!");
            return;
        }

        Collections.shuffle(this.player1Wrestlers);
        Collections.shuffle(this.player2Wrestlers);
        this.player1Card.setWrestler(this.player1Wrestlers.get(0));
        this.player2Card.setWrestler(this.player2Wrestlers.get(0));
        this.player1ScoreLabel.setText(Integer.toString(player1CardCount));
        this.player2ScoreLabel.setText(Integer.toString(player2CardCount));

        if ((this.turn % 2) == 1) {
            this.player1Card.open();
            this.player2Card.close();
        } else {
            this.player1Card.close();
            this.player2Card.open();
        }
    }
}
