package pl.company;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class MechanikaRozgrywki {
    private static int fate;
    Scanner scanner = new Scanner(System.in);
    boolean progress = true;
    Random rdm = new Random();
    int counter = 0;
    boolean passageProgress = false;
    public void mechanikaGry(Story story, Hero hero, Scanner scan, Scanner scane, Scanner scaner,
                             Random destiny, List<Monster> potworyLas, List<Monster> potworyGosciniec,
                             List<Monster> potworyDwor) {
        fate = 0;
        story.naming(hero);
        hero.setCurrentLv(1);

        while (scan.hasNext() && scane.hasNext() && scaner.hasNext()) {
            potworyLas.add(new Monster(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.next()));
            potworyGosciniec.add(new Monster(scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.next()));
            potworyDwor.add(new Monster(scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.next()));
        }
        Monster Boss = new Monster(500, 7, 15, 0, "MrocznyWładca");

        story.chapterI(hero);
        this.region(hero, potworyLas, destiny);
        story.chapterII(hero);
        this.region(hero, potworyGosciniec, destiny);
        story.chapterIII(hero);
        this.region(hero, potworyDwor, destiny);
        story.Boss();
        while (hero.deathCheck(hero) && Boss.deathCheck(Boss))
            arena(hero, Boss);
        if (hero.deathCheck(hero)) {
            System.out.println("Udało Ci się pokonać zło plugawiące Twój świat");
            System.out.println("Budzisz się nagle, to wszystko okazuje się snem, a Ty zaspałeś na Daily...");
        } else
            System.out.println("Mroczny Władca pochłania Twoją duszę");
    }




    // Funkcja generująca spotkania walki w danym regionie
    void region ( Hero hero, List<Monster> potwory, Random destiny ) {
        while (progress && hero.deathCheck(hero)) {
            fate = destiny.nextInt(potwory.size());
            chapters(hero, potwory.get(fate));
        }
    }

    //funkcja informująca o napotkanym wrogu
    public void encounter(Monster monster) {
        System.out.println();
        System.out.println("Niespodziewanie na twojej drodze staje " + monster.getName());
        System.out.println();

    }

    //Funkcja pytacjąca czy gracz chce pozostać w danym regionie, czy woli przejść do następnego
    public void passage(Hero hero) {
        if (counter >= 15) {
            while (!passageProgress) {
                System.out.println("Doszedłeś do końca, chcesz iść dalej czy wolisz pozostać i jeszcze się porozglądać?");
                System.out.println("pisz \"go\" żeby iść dalej, wpisz \"stay\", aby pozostać jeszcze chwilę się porozglądać ");
                switch (scanner.next()) {
                    case "go" -> {
                        System.out.println(hero.name + " bez lęku w sercu postanowił wyruszyć dalej");
                        counter = 0;
                        progress = false;
                        passageProgress = true;
                    }
                    case "stay" -> {
                        counter -= 5;
                        System.out.println("Tom wolał rozejrzeć się jeszcze, zanim wyruszy w dalszą podróż");
                        System.out.println(counter);
                        passageProgress = true;

                    }
                    default -> System.out.println("będna komenda");
                }

            }
            passageProgress = false;
        }
    }




    // Funkcja automatyzująca walkę, daje też możliwośc walki manualnej, zawiera w sobie licznik końca rozdziału, oraz przejście do następnego.
    public void chapters(Hero hero, Monster monster ){

        this.encounter(monster);
        System.out.println("wpisz man jeżeli chcesz walczyć manualnie");
        System.out.println("wpisz a jeżeli chcesz walczyć automatycznie");
        System.out.println("wpisz p jeżeli chcesz parować automatycznie");


        switch (scanner.next()) {
            case "man": {
                while (hero.deathCheck(hero) && monster.deathCheck(monster)) {
                    arena(hero, monster);
                }
            }
            break;
            case "a": {
                while (hero.deathCheck(hero) && monster.deathCheck(monster)) {
                    hero.AttackMonster(monster, hero);
                    if (monster.deathCheck(monster))
                        monster.dealDmg(hero);
                }
            }
            case "p": {
                while (hero.deathCheck(hero) && monster.deathCheck(monster)) {
                    int chance = rdm.nextInt(100) + 1;
                    System.out.println(chance);
                    if (chance <= 60 + hero.shieldAttackChanceModifier)
                        hero.parryAttackSuccess(monster, hero);
                    else
                        monster.parryAttackFail(hero);
                }
                if (monster.deathCheck(monster))
                    monster.dealDmg(hero);
            }
            break;

            default:
                System.out.println("Błędna komenda");
        }

        if (!hero.deathCheck(hero))
            progress = false;
        else
        {
            monster.setCurrentHP(monster.getHP());
            hero.info();
            passage(hero);
            counter++;
        }



    }
    // funkcja przyjmująca i zwierająca w walce bohatera i potwora, bohater wybiera co zrobi w czasie walki, po wybraniu walki manualnej
    public void arena(Hero hero, Monster monster) {
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



