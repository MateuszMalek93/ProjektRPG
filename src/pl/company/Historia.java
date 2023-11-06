package pl.company;

import java.util.Random;
import java.util.Scanner;

public class Historia {
    Random rdm = new Random();
    Scanner scanner = new Scanner(System.in);
    int counter = 0;
    boolean Progress = true;
    boolean PrzejscieProgres = false;

    //Funkcja pytająca o Imię Bohatera
    public void naming(Hero Hero) {
        System.out.println("Jak nazwiesz swojego Bohatera?");
        Hero.Namer(scanner.next());
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
        Progress = true;
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
        Progress = true;
    }

    //Fabularne wprowadzenie do walki z bossem
    public void Boss(){
        System.out.println("Ostatnia z piekielnych besti padła martwa, pozostał już tylko on Mroczny Władca");
        System.out.println("Wskazał na Bohetera Palcem i wysyczał \"Chodź i pożegnaj się ze swoją duszą\"");

    }

    //funkcja informująca o napotkanym wrogu
    public void Napotykasz(Monster monster) {
        System.out.println();
        System.out.println("Niespodziewanie na twojej drodze staje " + monster.getName());
        System.out.println();

    }



    // Funkcja sprawdzająca czy Bohater może przejśc do kolejego rozdziału. Bohater ma wybór może zostać i pokonywać dlalej
    // Potwory z tego rozdziału, lub może przejść do następnego rozdziału
    public void Passage() {
        if (counter >= 15) {
            while (!PrzejscieProgres) {
                System.out.println("Doszedłeś do końca, chcesz iść dalej czy wolisz pozostać i jeszcze się porozglądać?");
                System.out.println("pisz \"go\" żeby iść dalej, wpisz \"stay\", aby pozostać jeszcze chwilę się porozglądać ");
                switch (scanner.next()) {
                    case "go" -> {
                        System.out.println("Tom bez lęku w sercu postanowił wyruszyć dalej");//todo: Tom?
                        counter = 0;
                        Progress = false;
                        PrzejscieProgres = true;
                    }
                    case "stay" -> {
                        counter -= 5;
                        System.out.println("Tom wolał rozejrzeć się jeszcze, zanim wyruszy w dalszą podróż");
                        System.out.println(counter);
                        PrzejscieProgres = true;

                    }
                    default -> System.out.println("będna komenda");
                }

            }
            PrzejscieProgres = false;
        }
    }

// Funkcja automatyzująca walkę, daje też możliwośc walki manualnej, zawiera w sobie licznik końca rozdziału, oraz przejście do następnego.
    public void chapters(Hero Hero, Monster monster, Historia story) {

            story.Napotykasz(monster);
            System.out.println("wpisz man jeżeli chcesz walczyć manualnie");
            System.out.println("wpisz a jeżeli chcesz walczyć automatycznie");
            System.out.println("wpisz p jeżeli chcesz parować automatycznie");


            switch (scanner.next()) {
                case "man": {
                    while (Hero.deathCheck(Hero) && monster.deathCheck(monster)) {
                        story.Arena(Hero, monster);
                    }
                }
                break;
                case "a": {
                    while (Hero.deathCheck(Hero) && monster.deathCheck(monster)) {
                        Hero.AttackMonster(monster, Hero);
                        if (monster.deathCheck(monster))
                            monster.dealDmg(Hero);
                    }
                }
                case "p": {
                    while (Hero.deathCheck(Hero) && monster.deathCheck(monster)) {
                        int chance = rdm.nextInt(100) + 1;
                        System.out.println(chance);
                        if (chance <= 60 + Hero.shieldAttackChanceModifier)
                            Hero.parryAttackSuccess(monster, Hero);
                        else
                            monster.parryAttackFail(Hero);
                    }
                    if (monster.deathCheck(monster))
                        monster.dealDmg(Hero);
                }
                break;

                default:
                    System.out.println("Błędna komenda");
            }

            if (!Hero.deathCheck(Hero))
            Progress = false;
            else
            {
                monster.setCurrentHP(monster.getHP());
                Hero.info();
                story.Passage();
                story.counter++;
            }



        }
    // funkcja przyjmująca i zwierająca w walce bohatera i potwora, bohater wybiera co zrobi w czasie walki, po wybraniu walki manualnej
    public void Arena(Hero hero, Monster monster) {
        System.out.println();
        System.out.println("Wpisz a aby zaatakować");
        System.out.println("Wpisz p aby spróbować sparować cios");
        System.out.println("Wpisz m aby wypić miksturę (o ile ją masz)");
        switch (scanner.next()) {
            case "a" -> {
                hero.AttackMonster(monster, hero);
                if (monster.deathCheck(monster))
                    monster.dealDmg(hero);
            }
            case "p" -> {
                int chance = rdm.nextInt(100) + 1;
                System.out.println(chance);
                if (chance <= 60 + hero.shieldAttackChanceModifier)
                    hero.parryAttackSuccess(monster, hero);
                else
                    monster.parryAttackFail(hero);
            }
            case "m" -> {
                if (hero.potionQuantity == 0) {
                    System.out.println("Nie masz mikstur");
                    break;
                }
                int HP = hero.getCurrentHP();
                hero.usePotion();
                int wyleczoneHP = hero.getCurrentHP() - HP;

                System.out.println(hero.getName() + " Uzył miksturę leczniczą i przywrocił sobie " + wyleczoneHP + " HP");
                monster.dealDmg(hero);
            }
            default -> System.out.println("błędna komenda");
        }
    }



}