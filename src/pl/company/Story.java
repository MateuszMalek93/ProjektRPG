package pl.company;
import java.util.Scanner;

public class Story {
    Scanner scanner = new Scanner(System.in);
    boolean progress = true;

    //Funkcja pytająca o Imię Bohatera
    public void naming(Hero Hero) {
        System.out.println("Jak nazwiesz swojego Bohatera?");
        Hero.namer(scanner.next());
        System.out.println("A wiec o to historia bohatera o imieniu " + Hero.getName());
    }

    // Wprowadzenie fabularne do pierwszego rozdziału
    public void chapterI(Hero Hero) {
        System.out.println("Bohater co zwał się " + Hero.getName());
        System.out.println("" + "Wyruszył  do tajemniczego dworu któy ponoć był siedliskiem zła");
        System.out.println("Lecz najpierw musiał przejść przez stary las");
        System.out.println();
        System.out.println("ROZDZIAŁ I");
        System.out.println("STARY LAS");

    }

    // Wprowadzenie fabularne do drugiego rozdziału
    public void chapterII(Hero Hero) {
        System.out.println("Po wyjściu z lasu " + Hero.getName() + " rozbił obóz.");
        System.out.println("Tej nocy śnił kłach i pazurach które, bez ustanku chciały go dopaść");
        System.out.println("Pod koniec snu zobaczył twarz swojej ukochanej, co przepełniło go siła i determinacją");
        System.out.println(" ~Zdrowie uzupełnione ");
        Hero.setCurrentHP(Hero.getHP());
        System.out.println();
        System.out.println("ROZDZIAŁ II");
        System.out.println("OPUSZCZONY GOŚCINIEC");
        progress = true;
    }

    // Wprowadzenie fabularne do trzeciego rozdziału
    public void chapterIII(Hero Hero) {
        System.out.println("Po Przejściu Gościńca " + Hero.getName() + " rozbił obóz.");
        System.out.println("Tej nocy nie miał snów, może to i lepiej");
        System.out.println("Zwłaszcza, że w oddali majaczył już dwór owity złą sławą");
        System.out.println(" ~Zdrowie uzupełnione ");
        Hero.setCurrentHP(Hero.getHP());
        System.out.println();
        System.out.println("ROZDZIAŁ III");
        System.out.println("Dwór");
        progress = true;
    }

    //Fabularne wprowadzenie do walki z bossem
    public void Boss(){
        System.out.println("Ostatnia z piekielnych besti padła martwa, pozostał już tylko on Mroczny Władca");
        System.out.println("Wskazał na Bohetera Palcem i wysyczał \"Chodź i pożegnaj się ze swoją duszą\"");

    }


}