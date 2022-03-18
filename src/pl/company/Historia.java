package pl.company;

import java.util.Scanner;

public class Historia {
    Scanner scanner= new Scanner(System.in);

    public void Nazywanko(Bohater Hero){
        System.out.println("Jak nazwiesz swojego Bohatera?");
        Hero.Nazywacz(scanner.next());
        System.out.println("A wiec o to historia bohatera o imieniu "+Hero.getName());
    }
    public void wprowadzenie (Bohater Hero){
        System.out.println("Bohater co zwał się " + Hero.getName());
        System.out.println("Wyruszył  do tajemniczego dworu któy ponoć był siedliskiem zła");
        System.out.println("Lecz najpierw musiał przejść przez stary las");
        System.out.println();
        System.out.println("ROZDZIAŁ I");
        System.out.println("STARY LAS");

    }
}
