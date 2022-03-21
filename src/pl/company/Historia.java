package pl.company;

import java.util.Scanner;

public class Historia {
    Scanner scanner= new Scanner(System.in);

    //Funkcja pytająca o Imię Bohatera
    public void Nazywanko(Bohater Hero){
        System.out.println("Jak nazwiesz swojego Bohatera?");
        Hero.Nazywacz(scanner.next());
        System.out.println("A wiec o to historia bohatera o imieniu "+Hero.getName());
    }
    // Wprowadzenie fabularne do pierwszego rozdziału
    public void wprowadzenie1 (Bohater Hero){
        System.out.println("Bohater co zwał się " + Hero.getName());
        System.out.println("Wyruszył  do tajemniczego dworu któy ponoć był siedliskiem zła");
        System.out.println("Lecz najpierw musiał przejść przez stary las");
        System.out.println();
        System.out.println("ROZDZIAŁ I");
        System.out.println("STARY LAS");

    }

    //funkcja informująca o napotkanym wrogu
    public void Napotykasz(Potwór monster){
        System.out.println("Niespodziewanie na twojej drodze staje " +monster.getName());

    }
    // funkcja przyjmująca i zwierająca w walce bohatera i potwora, bohater wybiera co zrobi w czasie walki
    public void Arena (Bohater hero, Potwór monster){
        System.out.println("Wpisz a aby zaatakować");
        switch (scanner.next()){
            case "a": {
                hero.dealDmg(monster);
                if (monster.deathCheck(monster))
                    monster.dealDmg(hero);
            }break;
            default:
                System.out.println("błędna komenda");
        }
    }
}
