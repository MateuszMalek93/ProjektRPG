package pl.company;

import java.util.Random;

public abstract class Istotaa {
    Random rdm = new Random();
    private int HP;
    private int currentHP;
    private int dmgD;
    private int dmgU;
    private int Exp;
    private String name;

    //konstruktor
    public Istotaa(int hp, int dmgDOWN, int dmgUP, int exp, String Name) {
        HP = hp;
        dmgU = dmgUP;
        dmgD = dmgDOWN;
        Exp = exp;
        name = Name;
        currentHP = hp;
    }


    //Użycie mikstury
    public void usePotion(Bohater hero) {
        int HP = rdm.nextInt(hero.getHP() / 2 - getHP() / 10) + getHP() / 10;
        setCurrentHP(getCurrentHP() + HP);
        if (hero.getCurrentHP() > hero.getHP())
            hero.setCurrentHP(hero.getHP());
        hero.ilośćMikstur--;
    }


    //Udane parowanie
    public void parryAttackSuccess(Potwór monster, Bohater Hero) {
        int chance = rdm.nextInt(100) + 1;

        if (100 - Hero.modyfikatorTarczakryt >= chance) {
            int DMG = getAttackDmg();
            monster.LoseHP(DMG);
            System.out.println(name + " Sparował cios i zadał " + DMG + " obrażeń");
            if (this.deathCheck(monster))
                System.out.println(monster.getName() + " ma obecnie " + monster.getCurrentHP() + " HP.");
            else System.out.println(monster.getName() + " został pokonany");
            System.out.println();
        } else {
            int DMG = getAttackDmg() * 2;
            monster.LoseHP(DMG);
            System.out.println(name + " Sparował cios i zadał " + DMG + " obrażeń trafiając w czuły punkt");
            if (this.deathCheck(monster))
                System.out.println(monster.getName() + " ma obecnie " + monster.getCurrentHP() + " HP.");
            else System.out.println(monster.getName() + " został pokonany");
            System.out.println();
        }
    }

    //Nieudane parowanie
    public void parryAttackFail(Bohater hero) {
        System.out.println("Bohater zablokował część obrażeń");

        int DMG = getAttackDmg() / 2;
        hero.LoseHP(DMG);
        System.out.println(name + " zadał " + DMG + " obrażeń");
        if (this.deathCheck(hero))
            System.out.println(hero.getName() + " ma obecnie " + hero.getCurrentHP() + " HP.");
        else System.out.println(hero.getName() + " został pokonany. Koniec gry");
        System.out.println();
    }

    //Atak, może zadać obrażenia, spudłować, lub zadać obrażenia krytyczne
    public void AttackMonster(Potwór monster, Bohater hero) {
        int chance = rdm.nextInt(100) + 1;

        if (chance <= 10 - hero.modyfikatorMieczKryt) {
            System.out.println(monster.getName() + " wykonał unik");
        } else if (chance <= 90 - hero.modyfikatorMieczKryt) {
            dealDmg(monster);
        } else
            criticalHit(monster);
    }

    // funkcja odpowiedzialna za zadanie obrażeń krytycznych
    public void criticalHit(Potwór monster) {
        int DMG = 2 * getAttackDmg();
        monster.LoseHP(DMG);
        System.out.println(name + " udeżył w słaby punkt i zadał " + DMG + " obrażeń");
        if (this.deathCheck(monster))
            System.out.println(monster.getName() + " ma obecnie " + monster.getCurrentHP() + " HP.");
        else System.out.println(monster.getName() + " został pokonany");
        System.out.println();
    }

    //Zadawanie obrażeń potworowi wchodzącemu do funkcji
    public void dealDmg(Potwór monster) {
        int DMG = getAttackDmg();
        monster.LoseHP(DMG);
        System.out.println(name + " zadał " + DMG + " obrażeń");
        if (this.deathCheck(monster))
            System.out.println(monster.getName() + " ma obecnie " + monster.getCurrentHP() + " HP.");
        else System.out.println(monster.getName() + " został pokonany");
        System.out.println();
    }

    //Zadawanie obrażeń bohaterowi wchodzącemu do funkcji
    public void dealDmg(Bohater hero) {
        int DMG = getAttackDmg();
        hero.LoseHP(DMG);
        System.out.println(name + " zadał " + DMG + " obrażeń");
        if (this.deathCheck(hero))
            System.out.println(hero.getName() + " ma obecnie " + hero.getCurrentHP() + " HP.");
        else System.out.println(hero.getName() + " został pokonany. Koniec gry");
        System.out.println();
    }

    //funkcja odbierająca punkty życia
    public void LoseHP(int at) {
        this.currentHP -= at;
    }

    //funkcja sprawdzająca czy dany potwór żyje
    public Boolean deathCheck(Potwór monster) {
        int hp = monster.getCurrentHP();

        if (hp > 0)
            return true;
        else return false;
    }

    //funkcja sprawdzająca czy dany bohater żyje
    public Boolean deathCheck(Bohater hero) {
        int hp = hero.getCurrentHP();

        if (hp > 0)
            return true;
        else return false;
    }


    public void GainHP(int gainhp) {
        this.HP += gainhp;
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
