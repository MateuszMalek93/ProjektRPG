package pl.company;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class MechanikaRozgrywki {
    private static int fate;


    public MechanikaRozgrywki() {
    }


    void region (Historia story, Hero hero, List<Monster> potwory, Random destiny ) {
        while (story.Progress && hero.deathCheck(hero)) {
            fate = destiny.nextInt(potwory.size());
            story.chapters(hero, potwory.get(fate), story);
        }
    }

    public void mechanikaGry(Historia story, Hero hero, Scanner scan, Scanner scane, Scanner scaner,
                             Random destiny, List<Monster> potworyLas, List<Monster> potworyGosciniec,
                             List<Monster> potworyDwor) {
        fate = 0;
        story.naming(hero);//TODO: nazwy method rónież angielskie i zwyczajowo z małęj litery
        hero.setCurrentLv(1);//TODO

        while (scan.hasNext() && scane.hasNext() && scaner.hasNext()) {
            potworyLas.add(new Monster(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.next()));
            potworyGosciniec.add(new Monster(scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.next()));
            potworyDwor.add(new Monster(scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.next()));
        }
        Monster Boss = new Monster(500, 7, 15, 0, "MrocznyWładca");

        story.chapterI(hero);
        this.region(story, hero, potworyLas, destiny);
        story.chapterII(hero);
        this.region(story, hero, potworyGosciniec, destiny);
        story.chapterIII(hero);
        this.region(story, hero, potworyDwor, destiny);
        story.Boss();
        while (hero.deathCheck(hero) && Boss.deathCheck(Boss))
            story.Arena(hero, Boss);
        if (hero.deathCheck(hero)) {
            System.out.println("Udało Ci się pokonać zło plugawiące Twój świat");
            System.out.println("Budzisz się nagle, to wszystko okazuje się snem, a Ty zaspałeś na Daily...");
        } else
            System.out.println("Mroczny Władca pochłania Twoją duszę");
    }


    }


