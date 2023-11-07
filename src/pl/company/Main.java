package pl.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Random przeznaczenie = new Random();
        Scanner scan = new Scanner(new File("src/pl/company/PotworyLas"));
        Scanner scane = new Scanner(new File("src/pl/company/PotworyGosciniec"));
        Scanner scaner = new Scanner(new File("src/pl/company/PotworyDwor"));
        Hero Hero = new Hero(50, 2, 6, 0, " ");
        Story story = new Story();
        List<Monster> potworyLas = new ArrayList<>();
        List<Monster> potworyGosciniec = new ArrayList<>();
        List<Monster> potworyDwor = new ArrayList<>();
        MechanikaRozgrywki mr = new MechanikaRozgrywki();

        mr.mechanikaGry(story, Hero, scan, scane, scaner,
                przeznaczenie, potworyLas, potworyGosciniec,
                potworyDwor);
    }
}

