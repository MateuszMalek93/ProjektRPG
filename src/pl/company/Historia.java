package pl.company;

import java.util.Random;
import java.util.Scanner;

public class Historia {
    Random rdm = new Random();
    Scanner scanner = new Scanner(System.in);
    int counter = 0;
    boolean Progress= true;
    boolean PrzejscieProgres = false;

    //Funkcja pytająca o Imię Bohatera
    public void Nazywanko(Bohater Hero) {
        System.out.println("Jak nazwiesz swojego Bohatera?");
        Hero.Nazywacz(scanner.next());
        System.out.println("A wiec o to historia bohatera o imieniu " + Hero.getName());
    }

    // Wprowadzenie fabularne do pierwszego rozdziału
    public void rozdziałI(Bohater Hero) {
        System.out.println("Bohater co zwał się " + Hero.getName());
        System.out.println("Wyruszył  do tajemniczego dworu któy ponoć był siedliskiem zła");
        System.out.println("Lecz najpierw musiał przejść przez stary las");
        System.out.println();
        System.out.println("ROZDZIAŁ I");
        System.out.println("STARY LAS");

    }

    public void rozdziałII(Bohater Hero){
        System.out.println("Po wyjściu z lasu " +Hero.getName()+ " rozbił obóz.");
        System.out.println("Tej nocy śnił kłach i pazurach które, bez ustanku chciały go dopaść");
        System.out.println("Pod koniec snu zobaczył twarz swojej ukochanej, co przepełniło go siła i determinacją");
        System.out.println(" ~Zdrowie uzupełnione ");
        Hero.setCurrentHP(Hero.getHP());
        System.out.println();
        System.out.println("ROZDZIAŁ II");
        System.out.println("OPUSZCZONY GOŚCINIEC");
        Progress = true;
    }

    //funkcja informująca o napotkanym wrogu
    public void Napotykasz(Potwór monster) {
        System.out.println();
        System.out.println("Niespodziewanie na twojej drodze staje " + monster.getName());
        System.out.println();

    }

    // funkcja przyjmująca i zwierająca w walce bohatera i potwora, bohater wybiera co zrobi w czasie walki
    public void Arena(Bohater hero, Potwór monster) {
        System.out.println();
        System.out.println("Wpisz a aby zaatakować");
        System.out.println("Wpisz p aby spróbować sparować cios");
        System.out.println("Wpisz m aby wypić miksturę (o ile ją masz)");
        switch (scanner.next()) {
            case "a": {
                hero.AttackMonster(monster, hero);
                if (monster.deathCheck(monster))
                    monster.dealDmg(hero);
            }
            break;
            case "p": {
                int chance = rdm.nextInt(100) + 1;
                System.out.println(chance);
                if (chance <= 60 + hero.modyfikatorTarczaSzansa)
                    hero.parryAttackSuccess(monster, hero);
                else
                    monster.parryAttackFail(hero);
            }
            break;
            case "m":{
                if (hero.ilośćMikstur==0){
                    System.out.println("Nie masz mikstur");
                    break;
                }
                int HP = hero.getCurrentHP();
                hero.usePotion(hero);
                int wyleczoneHP= hero.getCurrentHP()-HP;

                System.out.println(hero.getName() + " Uzył miksturę leczniczą i przywrocił sobie " + wyleczoneHP + " HP");
                monster.dealDmg(hero);
            }break;
            default:
                System.out.println("błędna komenda");
        }
    }

    public void Przejście() {
        if (counter >= 10) {
            while (!PrzejscieProgres){
            System.out.println("Doszedłeś do końca, chcesz iść dalej czy wolisz pozostać i jeszcze się porozglądać?");
            System.out.println("pisz \"go\" żeby iść dalej, wpisz \"stay\", aby pozostać jeszcze chwilę się porozglądać ");
            switch (scanner.next()) {
                case "go": {
                    System.out.println("Tom bez lęku w sercu postanowił wyruszyć dalej");
                    counter = 0;
                    Progress = false;
                    PrzejscieProgres = true;
                }
                break;
                case "stay": {
                    counter -= 5;
                    System.out.println("Tom wolał rozejrzeć się jeszcze, zanim wyruszy w dalszą podróż");
                    System.out.println(counter);
                    PrzejscieProgres = true;

                }
                break;
                default:
                    System.out.println("będna komenda");
            }

            }
            PrzejscieProgres=false;
        }
    }
}