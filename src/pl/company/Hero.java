package pl.company;

import java.util.Scanner;

public class Hero extends Being {
    int lvUpPoint = 200;
    int currentLV = 1;
    int attackModifier = 0;
    int critSwordAttackModifier = 0;
    int critShieldAttackModifier = 0;
    int shieldAttackChanceModifier = 0;
    String rank = "Pachołek";
    Scanner scan = new Scanner(System.in);
    int potionQuantity = 3;

    public Hero(int hp, int dmgDOWN, int dmgUP, int exp, String Name) {
        super(hp, dmgDOWN, dmgUP, exp, Name);
    }

    //funkcja nazywająca bohatera
    public void Namer(String name) {
        this.setName(name);
    }

    //funkcja sprawdzająca czy bohater kwalifikuje się do awansu na wyższy poziom
    public void lvUpCheck() {
        if (getExp() >= getLvUpPoint()) {
            setCurrentLv(getCurrentLv() + 1);
            setExp(getExp() - getLvUpPoint());
            setLvUpPoint(getLvUpPoint() * 2 - getLvUpPoint() / 15 * 10);
            System.out.println(getName() + " awansował na " + getCurrentLv() + " poziom");
            lvUpGains();
        }

    }

    //Funkcja zwiększająca lv i statystyki
    public void lvUpGains() {
        setHP(getHP() + 10);
        setCurrentHP(getHP());
        if (getCurrentLv() % 2 == 0)
            setDmgD(getDmgD() + 1);
        else
            setDmgU(getDmgU() + 1);

        if (getCurrentLv() == 5)
            Rank5lv();

        if (getCurrentLv() == 10)
            Rang10lv();

        if (getCurrentLv() == 15)
            Rang15lv();


        System.out.println();
    }

    //Funkcja dodająca rangę na 5 lv
    public void Rank5lv(){
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Wojownikiem");
            setRank("Wojownik");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"t\" Tarczownik - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne");
            System.out.println("Wpisz \"m\" Miecznik - +2 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, jego atak zawsze trafia");

            switch (scan.next()) {
                case "m" -> {
                    attackModifier += 2;
                    critSwordAttackModifier += 15;
                    setRank("Wojownik - Miecznik ");
                    progresRanga = true;
                }
                case "t" -> {
                    shieldAttackChanceModifier += 10;
                    critShieldAttackModifier += 25;
                    setRank("Wojownik - Tarczownik");
                    progresRanga = true;
                }
                default -> System.out.println("Błędna komenda");
            }
        }


    }

    //Funkcja dodająca rangę na 10 lv
    public void Rang10lv (){
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Mistrzem");
            setRank("Mistrz");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"h\" Huskarl - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne (większa szansa)");
            System.out.println("Wpisz \"s\" Szarmierz - +4 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, atak zawsze trafia");

            switch (scan.next()) {
                case "s" -> {
                    attackModifier += 4;
                    critSwordAttackModifier += 20;
                    setRank("Mistrz - Szermierz ");
                    progresRanga = true;
                }
                case "h" -> {
                    if (shieldAttackChanceModifier != 0) {
                        shieldAttackChanceModifier += 5;
                        critShieldAttackModifier += 5;
                    } else {
                        shieldAttackChanceModifier += 10;
                        critShieldAttackModifier += 25;
                        setRank("Mistrz - Huskarl");
                    }
                    progresRanga = true;
                }
                default -> System.out.println("Błędna komenda");
            }
        }

    }

    //Funkcja dodająca rangę na 15 lv
    public void Rang15lv (){
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Mistrzem");
            setRank("Czempion");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"p\" Pawężnik - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne (większa szansa)");
            System.out.println("Wpisz \"m\" Mistrz Miecza - +6 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, atak zawsze trafia");

            switch (scan.next()) {
                case "m" -> {
                    attackModifier += 5;
                    critSwordAttackModifier += 25;
                    setRank("Champion - Mistrz Miecza ");
                    progresRanga = true;
                }
                case "p" -> {
                    if (shieldAttackChanceModifier != 0) {
                        shieldAttackChanceModifier += 5;
                        critShieldAttackModifier += 5;
                    } else {
                        shieldAttackChanceModifier += 15;
                        critShieldAttackModifier += 25;
                        setRank("Champion - Pawężnik");
                    }
                    progresRanga = true;
                }
                default -> System.out.println("Błędna komenda");
            }
        }


    }

    //funkcja zwracająca informacje o bohaterze iloś HP, min max DMG i LV
    public void info() {
        System.out.println();
        System.out.println(getName());
        System.out.println("HP " + getCurrentHP() + "/" + getHP());
        System.out.print("DMG " + getDmgD() + "-" + getDmgU());
        if (attackModifier > 0)
            System.out.println(" + " + attackModifier);
        else
            System.out.println();
        System.out.println("LV " + getCurrentLv());
        System.out.println("Exp - " + getExp() + "/" + getLvUpPoint());
        System.out.println("Ranga " + getRanga());
        System.out.println("Mikstury - " + potionQuantity);
        System.out.println();
    }


    // Zmodyfikowana funkcja deal dmg dodająca exp bohaterowi po wygranej walce
    @Override
    public void dealDmg(Monster monster) {
        int chance = rdm.nextInt(100) + 1;
        super.dealDmg(monster);
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            if (chance >= 90) {
                potionQuantity++;
                System.out.println("Znalazłeś miksturę!");
            }
            lvUpCheck();
        }
    }

    @Override
    public void criticalHit(Monster monster) {
        int chance = rdm.nextInt(100) + 1;
        super.criticalHit(monster);
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            if (chance >= 80) {
                potionQuantity++;
                System.out.println("Znalazłeś miksturę!");
            }
            lvUpCheck();
        }
    }

    @Override
    public void parryAttackSuccess(Monster monster, Hero hero) {
        int chance = rdm.nextInt(100) + 1;
        super.parryAttackSuccess(monster, hero);
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            if (chance >= 85) {
                potionQuantity++;
                System.out.println("Znalazłeś miksturę!");
            }
            lvUpCheck();
        }

    }

    //Użycie mikstury
    public void usePotion() {//TODO: po co przekazujemy hero??? czy ta funkcja nie powinna być w klasie Bohater?
        int HP = rdm.nextInt(getHP() / 2 - getHP() / 10) + getHP() / 10;//TODO:czy to odwołujemy się dwa razy to tego samego,
        setCurrentHP(getCurrentHP() + HP);
        if (getCurrentHP() > getHP())
            setCurrentHP(getHP());
        potionQuantity--;
    }

    //SETERY GETERY
    public int getCurrentLv() {
        return currentLV;
    }

    public int getLvUpPoint() {
        return lvUpPoint;
    }

    public void setCurrentLv(int currentLV) {
        this.currentLV = currentLV;
    }

    public void setLvUpPoint(int lvUpPoint) {
        this.lvUpPoint = lvUpPoint;
    }

    public String getRanga() {
        return rank;
    }

    public void setRank(String ranga) {
        this.rank = ranga;
    }


    @Override
    public int getAttackDmg() {
        return super.getAttackDmg() + attackModifier;

    }
}

