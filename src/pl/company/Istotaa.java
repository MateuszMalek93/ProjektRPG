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

    public Istotaa(int hp, int dmgDOWN, int dmgUP, int exp, String Name) {
        HP = hp;
        dmgU = dmgUP;
        dmgD = dmgDOWN;
        Exp = exp;
        name = Name;
        currentHP = hp;
    }

    public void dealDmg(Potwór monster) {
        monster.LoseHP(this.getAttackDmg());
        System.out.println(this.name + " zadał " + this.getAttackDmg() + " obrażeń");
        if (this.deathCheck(monster))
            System.out.println(monster.getName() + " ma obecnie " + monster.getHP() + " HP.");
        else System.out.println(monster.getName() + " został pokonany");
        System.out.println();
    }

    public void dealDmg(Bohater hero) {
        hero.LoseHP(this.getAttackDmg());
        System.out.println(this.name + " zadał " + this.getAttackDmg() + " obrażeń");
        if (this.deathCheck(hero))
            System.out.println(hero.getName() + " ma obecnie " + hero.getHP() + " HP.");
        else System.out.println(hero.getName() + " został pokonany");
        System.out.println();
    }

    public void LoseHP(int at) {
        this.currentHP -= at;
    }

    public Boolean deathCheck(Potwór monster) {
        int hp = monster.getCurrentHP();

        if (hp > 0)
            return true;
        else return false;
    }

    public Boolean deathCheck(Bohater hero) {
        int hp = hero.getCurrentHP();

        if (hp > 0)
            return true;
        else return false;
    }

    public Boolean deathCheck(int hp) {
        if (hp <= 0)
            return false;
        else return true;
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
