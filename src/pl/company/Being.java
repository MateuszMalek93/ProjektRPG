package pl.company;

import java.util.Random;

public abstract class Being {
    Random rdm = new Random();
    private int HP;
    private int currentHP;
    private int dmgD;
    private int dmgU;
    private int Exp;
    public String name;

    //konstruktor
    public Being(int hp, int dmgDOWN, int dmgUP, int exp, String Name) {
        HP = hp;
        dmgU = dmgUP;
        dmgD = dmgDOWN;
        Exp = exp;
        name = Name;
        currentHP = hp;
    }


    //Udane parowanie
    public void parryAttackSuccess(Monster monster, Hero Hero) {
        int chance = rdm.nextInt(100) + 1;

        int DMG;
        if (100 - Hero.critShieldAttackModifier >= chance) {
            DMG = getAttackDmg();
            monster.LoseHP(DMG);
            System.out.println(name + " Sparował cios i zadał " + DMG + " obrażeń");
        } else {
            DMG = getAttackDmg() * 2;
            monster.LoseHP(DMG);
            System.out.println(name + " Sparował cios i zadał " + DMG + " obrażeń trafiając w czuły punkt");
        }
        if (this.deathCheck(monster))
            System.out.println(monster.getName() + " ma obecnie " + monster.getCurrentHP() + " HP.");
        else System.out.println(monster.getName() + " został pokonany");
        System.out.println();
    }

    //Nieudane parowanie
    public void parryAttackFail(Hero hero) {
        System.out.println("Bohater zablokował część obrażeń");

        int DMG = getAttackDmg() / 2;
        hero.LoseHP(DMG);
        System.out.println(name + " zadał " + DMG + " obrażeń");
        if (this.deathCheck(hero))
            System.out.println(hero.getName() + " ma obecnie " + hero.getCurrentHP() + " HP.");
        else {
            System.out.println(hero.getName() + " został pokonany. Koniec gry");
            System.exit(0);
        }
        System.out.println();
    }

    //Atak, może zadać obrażenia, spudłować, lub zadać obrażenia krytyczne
    public void AttackMonster(Monster monster, Hero hero) {
        int chance = rdm.nextInt(100) + 1;

        if (chance <= 10 - hero.critSwordAttackModifier) {
            System.out.println(monster.getName() + " wykonał unik");
        } else if (chance <= 90 - hero.critSwordAttackModifier) {
            dealDmg(monster);
        } else
            criticalHit(monster);
    }

    // funkcja odpowiedzialna za zadanie obrażeń krytycznych
    public void criticalHit(Monster monster) {
        int DMG = 2 * getAttackDmg();
        monster.LoseHP(DMG);
        System.out.println(name + " udeżył w słaby punkt i zadał " + DMG + " obrażeń");
        if (this.deathCheck(monster))
            System.out.println(monster.getName() + " ma obecnie " + monster.getCurrentHP() + " HP.");
        else System.out.println(monster.getName() + " został pokonany");
        System.out.println();
    }

    //Zadawanie obrażeń potworowi wchodzącemu do funkcji
    public void dealDmg(Monster monster) {
        int DMG = getAttackDmg();
        monster.LoseHP(DMG);
        System.out.println(name + " zadał " + DMG + " obrażeń");
        if (this.deathCheck(monster) )
            System.out.println(monster.getName() + " ma obecnie " + monster.getCurrentHP() + " HP.");
        else System.out.println(monster.getName() + " został pokonany");
        System.out.println();
    }

    //Zadawanie obrażeń bohaterowi wchodzącemu do funkcji
    public void dealDmg(Hero hero) {
        int DMG = getAttackDmg();
        hero.LoseHP(DMG);
        System.out.println(name + " zadał " + DMG + " obrażeń");
        if (this.deathCheck(hero))
            System.out.println(hero.getName() + " ma obecnie " + hero.getCurrentHP() + " HP.");
        else {
            System.out.println(hero.getName() + " został pokonany. Koniec gry");
            System.exit(0);
        }
        System.out.println();
    }

    //funkcja odbierająca punkty życia
    public void LoseHP(int at) {
        this.currentHP -= at;
    }

    //funkcja sprawdzająca czy dany potwór żyje
    public Boolean deathCheck(Monster monster) {
        int hp = monster.getCurrentHP();
        return hp > 0;
    }

    //funkcja sprawdzająca czy dany bohater żyje
    public Boolean deathCheck(Hero hero) {
        int hp = hero.getCurrentHP();

        return hp > 0;
    }

    // SETERY GETERY
    public String getName() {
        return name;
    }

    public int getAttackDmg() {

        return rdm.nextInt(dmgU + 1 - dmgD) + dmgD;
    }

    public int getExp() {
        return Exp;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setDmgD(int dmgD) {
        this.dmgD = dmgD;
    }

    public void setDmgU(int dmgU) {
        this.dmgU = dmgU;
    }

    public void setExp(int exp) {
        Exp = exp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDmgD() {
        return dmgD;
    }

    public int getDmgU() {
        return dmgU;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
}
