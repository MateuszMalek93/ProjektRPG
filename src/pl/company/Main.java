package pl.company;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Hero Hero = new Hero(50, 2, 6, 0, " ");
        Story story = new Story();
        GameMechanics gm = new GameMechanics();
        gm.game(story, Hero);
    }
}

