package pl.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Random przeznaczenie = new Random();
        int los = 0;
        Scanner scan = new Scanner(new File("src/pl/company/PotworyLas"));
        Scanner scane = new Scanner(new File("src/pl/company/PotworyGosciniec"));
        Scanner sc = new Scanner(System.in);
        Bohater Hero = new Bohater(50, 2, 6, 0, " ");
        Historia story = new Historia();
        story.Nazywanko(Hero);
        story.rozdziałI(Hero);
        Hero.getCurrentLV();
        Hero.setCurrentLV(1);
        //Hero.setCurrentHP(1);


        List<Potwór> potworyLas = new ArrayList<>();
        List<Potwór> poworyGosciniec = new ArrayList<>();

        while (scan.hasNext() && scane.hasNext()) {
            potworyLas.add(new Potwór(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.next()));
            poworyGosciniec.add(new Potwór(scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.next()));
        }


        while (story.Progress) {
            los = przeznaczenie.nextInt(potworyLas.size());
            story.Napotykasz(potworyLas.get(los));
            System.out.println("wpisz man jeżeli chcesz walczyć manualnie");
            System.out.println("wpisz a jeżeli chcesz walczyć automatycznie");
            System.out.println("wpisz p jeżeli chcesz parować automatycznie");


            switch (sc.next()) {
                case "man": {
                    while (Hero.deathCheck(Hero) && potworyLas.get(los).deathCheck(potworyLas.get(los))) {
                        story.Arena(Hero, potworyLas.get(los));
                    }
                }
                break;
                case "aut": {
                    while (Hero.deathCheck(Hero) && potworyLas.get(los).deathCheck(potworyLas.get(los))) {
                        Hero.AttackMonster(potworyLas.get(los), Hero);
                        if (potworyLas.get(los).deathCheck(potworyLas.get(los)))
                            potworyLas.get(los).dealDmg(Hero);
                    }
                }
                    case "p": {
                        while (Hero.deathCheck(Hero) && potworyLas.get(los).deathCheck(potworyLas.get(los))) {
                            int chance = przeznaczenie.nextInt(100)+1;
                            System.out.println(chance);
                            if (chance <= 60 + Hero.modyfikatorTarczaSzansa)
                                Hero.parryAttackSuccess(potworyLas.get(los), Hero);
                            else
                                potworyLas.get(los).parryAttackFail(Hero);}
                        if (potworyLas.get(los).deathCheck(potworyLas.get(los)))
                            potworyLas.get(los).dealDmg(Hero);
                    }
                    break;

                default:
                    System.out.println("Błędna komenda");
            }


            if (!Hero.deathCheck(Hero))
                break;
            potworyLas.get(los).setCurrentHP(potworyLas.get(los).getHP());
            Hero.info();
            story.Przejście();
            story.counter++;



        }
        if(Hero.deathCheck(Hero))
        story.rozdziałII(Hero);

        while (story.Progress && Hero.deathCheck(Hero)) {
            los = przeznaczenie.nextInt(poworyGosciniec.size());
            story.Napotykasz(poworyGosciniec.get(los));
            System.out.println("wpisz man jeżeli chcesz walczyć manualnie");
            System.out.println("wpisz a jeżeli chcesz walczyć automatycznie");
            System.out.println("wpisz p jeżeli chcesz parować automatycznie");

            switch (sc.next()) {
                case "man": {
                    while (Hero.deathCheck(Hero) && poworyGosciniec.get(los).deathCheck(poworyGosciniec.get(los))) {
                        story.Arena(Hero, poworyGosciniec.get(los));
                    }
                }
                break;
                case "a": {
                    while (Hero.deathCheck(Hero) && poworyGosciniec.get(los).deathCheck(poworyGosciniec.get(los))) {
                        Hero.AttackMonster(poworyGosciniec.get(los), Hero);
                        if (poworyGosciniec.get(los).deathCheck(poworyGosciniec.get(los)))
                            poworyGosciniec.get(los).dealDmg(Hero);
                    }

                    }

                    break;
                    case "p": {
                        while (Hero.deathCheck(Hero) && poworyGosciniec.get(los).deathCheck(poworyGosciniec.get(los))) {
                            int chance = przeznaczenie.nextInt(100)+1;
                            System.out.println(chance);
                            if (chance <= 60 + Hero.modyfikatorTarczaSzansa)
                                Hero.parryAttackSuccess(poworyGosciniec.get(los), Hero);
                            else
                                poworyGosciniec.get(los).parryAttackFail(Hero);}
                            if (poworyGosciniec.get(los).deathCheck(poworyGosciniec.get(los)))
                                poworyGosciniec.get(los).dealDmg(Hero);
                    }

                default:
                    System.out.println("Błędna komenda");
            }


            if (!Hero.deathCheck(Hero))
                break;
            poworyGosciniec.get(los).setCurrentHP(poworyGosciniec.get(los).getHP());
            Hero.info();
            story.counter++;
            story.Przejście();

        }

    }
}

