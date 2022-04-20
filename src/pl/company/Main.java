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
        Scanner scaner = new Scanner(new File("src/pl/company/PotworyDwor"));
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
        List<Potwór> poworyDwor = new ArrayList<>();

        while (scan.hasNext() && scane.hasNext() && scaner.hasNext()) {
            potworyLas.add(new Potwór(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.next()));
            poworyGosciniec.add(new Potwór(scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.next()));
            poworyDwor.add(new Potwór(scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.next()));
        }


        while (story.Progress){
            los = przeznaczenie.nextInt(potworyLas.size());
            story.Rozdziały(Hero,potworyLas.get(los),story);
        }

        if (Hero.deathCheck(Hero))
            story.rozdziałII(Hero);

        while (story.Progress){
            los = przeznaczenie.nextInt(poworyGosciniec.size());
            story.Rozdziały(Hero,poworyGosciniec.get(los),story);
        }

        if (Hero.deathCheck(Hero))
            story.rozdziałII(Hero);

        while (story.Progress){
            los = przeznaczenie.nextInt(poworyDwor.size());
            story.Rozdziały(Hero,poworyDwor.get(los),story);
        }

    }
}

