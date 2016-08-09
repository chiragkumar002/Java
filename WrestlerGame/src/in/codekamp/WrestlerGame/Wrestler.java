package in.codekamp.WrestlerGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cerebro on 08/04/16.
 */
public class Wrestler {
    private String name;
    private int rank;
    private int matchesWon;
    private int matchesLost;
    private Image image = null;

    private Wrestler(String name, int rank, int matchesWon, int matchesLost) {
        this.name = name;
        this.rank = rank;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
    }

    public static List<Wrestler> getAllWrestlers() {
        List<Wrestler> allWrestlers = new ArrayList<Wrestler>();
        allWrestlers.add(new Wrestler("Under Taker", 1, 100, 15));
        allWrestlers.add(new Wrestler("Rock", 4, 70, 18));
        allWrestlers.add(new Wrestler("HHH", 12, 85, 27));
        allWrestlers.add(new Wrestler("Hulk Hogan", 5, 120, 30));

        return allWrestlers;
    }

    @Override
    public String toString() {
        return "name: " + this.name + ", rank: " + this.rank;
    }

    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }

    public int getMatchesWon() {
        return this.matchesWon;
    }

    public int getMatchesLost() {
        return this.matchesLost;
    }

    public Image getImage() {

        if(this.image == null) {
            try {
                this.image = ImageIO.read(Wrestler.class.getResource("images/" + this.getRank() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this.image;
    }

    public int compare(Wrestler opponentWrestler, int criteria) {
        switch (criteria) {
            case 0:
                return opponentWrestler.getRank() - this.getRank();
            case 1:
                return this.getMatchesWon() - opponentWrestler.getMatchesWon();
            default:
                return this.getMatchesLost() - opponentWrestler.getMatchesLost();
        }
    }
}
