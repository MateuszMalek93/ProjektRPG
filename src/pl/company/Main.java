package pl.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws  FileNotFoundException {
        Random przeznaczenie = new Random();
        int los = 0;
        Scanner scan = new Scanner(new File("src/pl/company/PotworyLas"));
        Scanner sc = new Scanner(System.in);
        Bohater Hero = new Bohater(50, 2, 6, 0, " ");
        Historia story = new Historia();
        story.Nazywanko(Hero);
        //story.wprowadzenie(Hero);
        Hero.getCurrentLV();


        List<Potwór> potworyLas = new ArrayList<>();

        while(scan.hasNext()) {
            potworyLas.add(new Potwór(scan.nextInt(),scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.next()));
        }


        for (int i = 10;i>0; i--) {
            los = przeznaczenie.nextInt(potworyLas.size());
            story.Napotykasz(potworyLas.get(los));


                switch (sc.next()){
                    case"man":{
                        while (Hero.deathCheck(Hero) && potworyLas.get(los).deathCheck(potworyLas.get(los))) {
                        story.Arena(Hero,potworyLas.get(los));
                        }
                    }break;
                    case "aut":{
                        while (Hero.deathCheck(Hero) && potworyLas.get(los).deathCheck(potworyLas.get(los))) {
                            Hero.dealDmg(potworyLas.get(los));
                            if (potworyLas.get(los).deathCheck(potworyLas.get(los)))
                                potworyLas.get(los).dealDmg(Hero);
                        }
                                    ;break;
                    }
                    default:
                        System.out.println("jeszcze raz");
                }


            if (!Hero.deathCheck(Hero))
                break;
            potworyLas.get(los).setCurrentHP(potworyLas.get(los).getHP());
            Hero.info();
        }

    }
}
