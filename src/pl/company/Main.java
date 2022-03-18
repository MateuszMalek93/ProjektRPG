package pl.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bohater Hero = new Bohater(50, 2, 6, 0, " ");
        Historia story = new Historia();
        story.Nazywanko(Hero);
        //story.wprowadzenie(Hero);
        Hero.getCurrentLV();

        Potwór d = new Potwór(32, 1, 4, 300, "Szkielet");
        Potwór z = new Potwór(40, 1, 8, 10, "Zombie");

        while (Hero.deathCheck(Hero) && d.deathCheck(d)) {
            Hero.dealDmg(d);
            if (d.deathCheck(d))
            d.dealDmg(Hero);
        }
        Hero.info();
    }
}
